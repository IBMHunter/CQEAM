package com.sino.ams.task.scheduler.soa.td.read;

import com.sino.ams.task.service.soa.td.read.TDEmployeeReadService;
import com.sino.base.exception.DataHandleException;

/**
 * <p>标题: SinoEAM中国移动资产管理系统后台同步任务：任务层对象</p>
 * <p>描述: 通过SOA服务读取TD系统员工信息</p>
 * <p>版权: 根据中华人民共和国相关法律以及中华人民共和国加入的相关国际公约审定</p>
 * <p>开发商: 北京思诺博信息技术有限公司</p>
 *
 * @author 唐明胜
 * @version 1.0
 */
public class TDEmployeeReadTask {

    /**
     * <p>功能说明：从TD系统同步员工信息到EAM系统，</p>
     * <p>特殊说明，含以下两类信息：</p>
     * <li>员工基本信息；</li>
     * <li>员工分配信息；</li>
     *
     * @throws DataHandleException 同步数据出错时抛出数据处理异常
     */

    public void readTDEmployees() throws DataHandleException {
        TDEmployeeReadService service = new TDEmployeeReadService();
        service.readTDEmployee();
    }
}
