import { defHttp } from '/@/utils/http/axios';

enum Api {
  loginfo = '/sys/loginfo',
  visitInfo = '/sys/visitInfo',
  dessension = '/dissension/dissension/getCount'
}
/**
 * 日志统计信息
 * @param params
 */
export const getLoginfo = (params) => defHttp.get({ url: Api.loginfo, params }, { isTransformResponse: false });
/**
 * 访问量信息
 * @param params
 */
export const getVisitInfo = (params) => defHttp.get({ url: Api.visitInfo, params }, { isTransformResponse: false });
/**
 * 矛盾纠纷所有数据
 * @param params
 */
export const getDessensionData = () => defHttp.get({ url: Api.dessension }, { isTransformResponse: false });
