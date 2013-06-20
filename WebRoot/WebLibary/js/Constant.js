/**
 * @author mshtang
 */

//===============================================================================================
//��һ���֣����¶��屾�������õ��ĳ���
//===============================================================================================

/**
 * 1.���屾Javascript���������ͨ����
 */
var EMPTY_SPACE = " ";
//�ո��ַ���
var NUM_STR = "0123456789";
//�����ַ���;

/**
 * 2.���屾Javascript���������ܽ��ܵ����ڸ�ʽ����
 */

var DATE_STANDARD_PATTERN = "YYYYMMDD";
var DATE_LINE_PATTERN = "YYYY-MM-DD";
var DATE_SLOPE_PATTERN = "YYYY/MM/DD";
var DATE_DOT_PATTERN = "YYYY.MM.DD";
var DATE_CHINESE_PATTERN = "YYYY��MM��DD��";
var LIMIT_DATE_PATTERN = new Array(DATE_STANDARD_PATTERN, DATE_LINE_PATTERN, DATE_SLOPE_PATTERN, DATE_DOT_PATTERN, DATE_CHINESE_PATTERN);

/**
 * 3.���屾Javascript����������֧�ֵ���ͨ��׼У�鷽ʽ���Լ���׼��ʾ��Ϣ
 */

var EMPTY_VALIDATE = "isEmpty";
var DATE_VALIDATE = "isFormatDate";
var EMAIL_VALIDATE = "isEmail";
var NUMBER_VALIDATE = "isNumber";
var INT_VALIDATE = "isInt";
var DOUBLE_VALIDATE = "isDouble";
var LENGTH_VALIDATE = "isLengthValid";
var POSITIVE_VALIDATE = "isPositiveNumber";
var POSITIVE_INT_VALIDATE = "isPositiveInteger";
var DISCOUNT_VALIDATE = "isDiscount";
var VALIDATE_TYPE_ARR = new Array(EMPTY_VALIDATE, DATE_VALIDATE, EMAIL_VALIDATE, NUMBER_VALIDATE, INT_VALIDATE, DOUBLE_VALIDATE, LENGTH_VALIDATE, POSITIVE_VALIDATE, POSITIVE_INT_VALIDATE, DISCOUNT_VALIDATE);

/**
 * 4.���屾Javascript�������ṩ�ı�׼��ʾ��Ϣ
 */

var ALERT_MAG_HEAD = "����Ƿ���ԭ���ǣ�";
//��ʾ��Ϣ��ͷ���֣�
var EMPTY_MESSAGE = "������д��ѡ�񣬲���Ϊ�գ�";
var DATE_MESSAGE = "Ҫ�������ʽΪ$�����ڡ������ʽ��ȷ����������������Ƿ񲻴��ڣ�";
var EMAIL_MESSAGE = "Ҫ�������ʽ�Ϸ���Email��";
var NUMBER_MESSAGE = "Ҫ������Ϸ����֣�";
var INT_MESSAGE = "Ҫ������Ϸ�������";
var DOUBLE_MESSAGE = "Ҫ���������֣�����С��λ��������$λ��";
var LENGTH_MESSAGE = "Ҫ���������ݵ��ַ�����";
var POSITIVE_MSG = "Ҫ�����������������";
var POSITIVE_INT_MSG = "Ҫ�������������������";
var DISCOUNT_MSG = "Ҫ�����������������(0,1]�ڣ�";
var ALERT_MSG_ARR = new Array(EMPTY_MESSAGE, DATE_MESSAGE, EMAIL_MESSAGE, NUMBER_MESSAGE, INT_MESSAGE, DOUBLE_MESSAGE, LENGTH_MESSAGE, POSITIVE_MSG, POSITIVE_INT_MSG, DISCOUNT_MSG);

/**
 * 5.���屾Javascript�������ڽ�������У��ʱ�����õ�Ĭ�����ڸ�ʽ
 */
var DEFAULT_DATE_PATTERN = DATE_LINE_PATTERN;

/**
 * 6.�����׼�ַ�����
 */
var STANDARD_STR = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.+-*/`~!@#$%^&*()_=|\\;:'\"<>,?/";

/**
 * 7.Ϊ����ֵ�ĺ�����Ҫ�ı���
 */
var FORM_FIELD_ENDUE_STR = "";
var FIELD_NAME_VALUE_SPLITOR = "$$$$$";
var FIELD_SPLITOR = "$$$$$$$";
/**
 * 8.��ҳ�����޸Ļ�ɾ�����ݿ��¼ʱ����ʾ��Ϣ
 */
var UPDATE_MSG = "һ��ֻ����ʾ���޸�һ����¼�����ܲ�ѡ���ѡ��";
var DELETE_CHECK_MSG = "û��ѡ��Ҫɾ���ļ�¼��һ�ο���ɾ��һ���������¼�������ܲ�ѡ��";
var UPDATE_CHECK_MSG = "û��ѡ��Ҫ���µļ�¼��һ�ο��Ը���һ���������¼�������ܲ�ѡ��";
var DELETE_MSG = "����ɾ��������޷��ָ���ȷ��Ҫɾ����Ҫ����������ȷ������ť����������ȡ������ť��";
var CANCEL_MSG = "ȷ���������ι����𣿼���������ȷ������ť������������ȡ������ť��";
var ENABLE_MSG = "ȷ����Ч�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��"
var DISABLE_MSG = "ȷ��ʧЧ�𣿼�����㡰ȷ������ť��������㡰ȡ������ť��";
