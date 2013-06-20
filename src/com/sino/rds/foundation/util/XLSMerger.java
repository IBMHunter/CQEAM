package com.sino.rds.foundation.util;

import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.record.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.*;

public class XLSMerger {
    private List<File> files = null;

    public XLSMerger() {
        files = new ArrayList<File>();
    }

    /**
     * ���ܣ����Ҫ�ϲ���Excel�ļ�
     *
     * @param file File
     */
    public void addFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            return;
        }
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return;
        }
        fileName = fileName.substring(index + 1).toLowerCase();
        if (!fileName.equalsIgnoreCase("xls") && !fileName.equalsIgnoreCase("xlsx")) {
            return;
        }
        if (!files.contains(file)) {
            files.add(file);
        }
    }

    /**
     * ���ܣ�����Ҫ�ϲ���Excel�ļ����������ء�
     *
     * @param file String
     */
    public void addFile(String file) {
        if (StrUtil.isEmpty(file)) {
            return;
        }
        File tFile = new File(file);
        addFile(tFile);
    }

    /**
     * ���ܣ�����Ҫ�ϲ��ļ���Ŀ¼��
     * �ϲ�ʱ���Ѹ�Ŀ¼�µ�����Excel�ļ��ϲ�������������Ŀ¼��
     *
     * @param filePath String
     */
    public void setSrcPath(String filePath) {
        if (StrUtil.isEmpty(filePath)) {
            return;
        }
        File parFile = new File(filePath);
        File[] files = parFile.listFiles();
        if (files != null) {
            this.files.clear();
            for (File file : files) {
                addFile(file);
            }
        }
    }

    /**
     * ���ܣ�����Ҫ������ļ��б�
     *
     * @param files List
     */
    public void setFiles(List files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        this.files.clear();
        int fileCount = files.size();
        for (int i = 0; i < fileCount; i++) {
            addFile((File) files.get(i));
        }
    }

    /**
     * ���ܣ����ļ��ϲ�ΪĿ���ļ�
     *
     * @param targetFile String
     */
    public void merge(String targetFile) {
        merge(targetFile, false);
    }

    /**
     * ���ܣ����ļ��ϲ�ΪĿ���ļ�
     *
     * @param targetFile String
     * @param deleteSrc  �Ƿ�ɾ��ԭ�ļ�
     */
    public void merge(String targetFile, boolean deleteSrc) {
        if (StrUtil.isEmpty(targetFile)) {
            return;
        }
        merge(new File(targetFile), deleteSrc);
    }

    /**
     * ���ܣ����ļ��ϲ�ΪĿ���ļ����������ء�
     * ��ɾ��ԭ�ļ���
     *
     * @param targetFile File
     */
    public void merge(File targetFile) {
        merge(targetFile, false);
    }

    /**
     * ���ܣ����ļ��ϲ�ΪĿ���ļ�
     *
     * @param targetFile File Ŀ���ļ�
     * @param deleteSrc  �Ƿ�ɾ��ԭ�ļ�
     */
    public void merge(File targetFile, boolean deleteSrc) {
        try {
            if (targetFile == null) {
                return;
            }
            int fileCount = files.size();
            if (fileCount <= 1) {
                return;
            }
            if (!targetFile.exists()) {
                targetFile.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(targetFile);
            InputStream[] ins = new InputStream[files.size()];
            for (int i = 0; i < fileCount; i++) {
                ins[i] = new FileInputStream(files.get(i));
            }
            merge(ins, out);
            if (deleteSrc) {
                for (int i = 0; i < fileCount; i++) {
                    files.get(i).delete();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.logError(ex);
        }
    }

    /**
     * ���ܣ������Xls�ļ��ϲ�Ϊһ����������ֻ��һ��sheet�����Ҹ�ʽ��ͬ���ĵ�
     *
     * @param ins �����Xls�ļ�
     * @param out ����ļ�
     */
    private static void merge(InputStream[] ins, OutputStream out) {
        List rootRecords = getRecords(ins[0]);
        Workbook workbook = Workbook.createWorkbook(rootRecords);
        List<Sheet> sheets = getSheets(workbook, rootRecords);
        if (sheets == null || sheets.size() == 0) {
            throw new IllegalArgumentException("��һƪ�ĵ��ĸ�ʽ���󣬱���������һ��sheet");
        }
        //�Ե�һƪ�ĵ������һ��sheetΪ�����Ժ�����ݶ�׷�������sheet����
        Sheet rootSheet = sheets.get(sheets.size() - 1);
        int rootRows = getRowsOfSheet(rootSheet); //��¼��һƪ�ĵ����������Ժ�������ڴ˻���������
        rootSheet.setLoc(rootSheet.getDimsLoc());
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(10000); //Integer, Integer
        for (int i = 1; i < ins.length; i++) { //�ӵڶ�ƪ��ʼ����
            List<Record> records = getRecords(ins[i]);
            int rowsOfCurXls = 0;
            for (Record record : records) { //������ǰ�ĵ���ÿһ��record
                if (record.getSid() == RowRecord.sid) { //�����RowRecord
                    RowRecord rowRecord = (RowRecord) record;
                    rowRecord.setRowNumber(rootRows + rowRecord.getRowNumber()); //�����к�
                    rootSheet.addRow(rowRecord); //׷��Row
                    rowsOfCurXls++; //��¼��ǰ�ĵ�������
                } else if (record.getSid() == SSTRecord.sid) { //SST��¼��SST����xls�ļ���Ψһ��String������String���Ƕ�Ӧ��SST��¼������
                    SSTRecord sstRecord = (SSTRecord) record;
                    for (int j = 0; j < sstRecord.getNumUniqueStrings(); j++) {
                        int index = workbook.addSSTString(sstRecord.getString(j));
                        map.put(j, index); //��¼ԭ�������������ڵ������Ķ�Ӧ��ϵ
                    }
                } else if (record.getSid() == LabelSSTRecord.sid) {
                    LabelSSTRecord label = (LabelSSTRecord) record;
                    int index = Integer.parseInt(String.valueOf(map.get(label.getSSTIndex())));
                    label.setSSTIndex(index); //����SST�����Ķ�Ӧ��ϵ
                }
                if (record instanceof CellValueRecordInterface) { //׷��ValueCell
                    CellValueRecordInterface cell = (CellValueRecordInterface) record;
                    int cellRow = cell.getRow() + rootRows;
                    cell.setRow(cellRow);
                    rootSheet.addValueRecord(cellRow, cell);
                }
            }
            rootRows += rowsOfCurXls;
        }
        byte[] data = getBytes(workbook, (Sheet[]) (sheets.toArray(new Sheet[0])));
        write(out, data);
    }

    /**
     * ���ܣ����ļ�д������
     *
     * @param out  OutputStream
     * @param data byte[]
     */
    private static void write(OutputStream out, byte[] data) {
        POIFSFileSystem fs = new POIFSFileSystem();
        try {
            fs.createDocument(new ByteArrayInputStream(data), "Workbook");
            fs.writeFilesystem(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ���ܣ�����sheets
     *
     * @param workbook Workbook
     * @param records  List
     * @return List
     */
    private static List<Sheet> getSheets(Workbook workbook, List records) {
        int recOffset = workbook.getNumRecords();
        int sheetNum = 0;
        convertLabelRecords(records, recOffset, workbook);
        List<Sheet> sheets = new ArrayList<Sheet>();
        while (recOffset < records.size()) {
            Sheet sh = Sheet.createSheet(records, sheetNum++, recOffset);
            recOffset = sh.getEofLoc() + 1;
            if (recOffset == 1) {
                break;
            }
            sheets.add(sh);
        }
        return sheets;
    }

    /**
     * ���ܣ���ȡsheet�����һ��
     *
     * @param sheet Sheet
     * @return int
     */
    private static int getRowsOfSheet(Sheet sheet) {
        int rows = 0;
        sheet.setLoc(0);
        while (sheet.getNextRow() != null) {
            rows++;
        }
        return rows;
    }

    /**
     * ���ܣ���ȡ��¼����
     *
     * @param in InputStream
     * @return List
     */
    private static List<Record> getRecords(InputStream in) {
        List<Record> records = Collections.EMPTY_LIST;
        try {
            POIFSFileSystem poifs = new POIFSFileSystem(in);
            InputStream stream = poifs.createDocumentInputStream("Workbook");
            records = RecordFactory.createRecords(stream);
        } catch (IOException ex) {
            Logger.logError(ex);
        }
        return records;
    }

    /**
     * ���ܣ�ת����ǩ��
     *
     * @param records  List
     * @param offset   int
     * @param workbook Workbook
     */
    private static void convertLabelRecords(List<LabelSSTRecord> records, int offset, Workbook workbook) {
        for (int k = offset; k < records.size(); k++) {
            Record rec = records.get(k);
            if (rec.getSid() == LabelRecord.sid) {
                LabelRecord oldrec = (LabelRecord) rec;
                records.remove(k);
                LabelSSTRecord newrec = new LabelSSTRecord();
                int stringid = workbook.addSSTString(new UnicodeString(oldrec.getValue()));
                newrec.setRow(oldrec.getRow());
                newrec.setColumn(oldrec.getColumn());
                newrec.setXFIndex(oldrec.getXFIndex());
                newrec.setSSTIndex(stringid);
                records.add(k, newrec);
            }
        }
    }

    /**
     * ���ܣ���ȡworkbook���ֽ�����
     *
     * @param workbook Workbook
     * @param sheets   Sheet[]
     * @return byte[]
     */
    private static byte[] getBytes(Workbook workbook, Sheet[] sheets) {
        int nSheets = sheets.length;
        for (int i = 0; i < nSheets; i++) {
            sheets[i].preSerialize();
        }
        int totalsize = workbook.getSize();
        int[] estimatedSheetSizes = new int[nSheets];
        for (int k = 0; k < nSheets; k++) {
            workbook.setSheetBof(k, totalsize);
            int sheetSize = sheets[k].getSize();
            estimatedSheetSizes[k] = sheetSize;
            totalsize += sheetSize;
        }
        byte[] retval = new byte[totalsize];
        int pos = workbook.serialize(0, retval);
        for (int k = 0; k < nSheets; k++) {
            int serializedSize = sheets[k].serialize(pos, retval);
            if (serializedSize != estimatedSheetSizes[k]) {
                throw new IllegalStateException("Actual serialized sheet size ("
                        + serializedSize
												+ ") differs from pre-calculated size ("
												+ estimatedSheetSizes[k]
												+ ") for sheet ("
												+ k
												+ ")");
			}
			pos += serializedSize;
		}
		return retval;
	}
}
