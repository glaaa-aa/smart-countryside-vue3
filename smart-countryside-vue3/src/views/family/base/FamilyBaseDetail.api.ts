import { defHttp } from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/family/familyBase/list',
  save = '/family/familyBase/addDetail',
  // edit = '/family/familyBase/edit',
  queryByFamilyAndYear = "/family/familyBase/queryByFamilyAndYear",
  deleteOne = '/family/familyBase/delete',
  deleteBatch = '/family/familyBase/deleteBatch',
  importExcel = '/family/familyBase/importExcel',
  exportXls = '/family/familyBase/exportXls',
  exportXlsUrl = "/family/familyBase/detailExportXls",//导出户详细信息
  importHouseExcelUrl = "family/house/importExcel",//导入住房信息
  exampleHouseXlsUrl = "/family/house/example/exportXls",//导出住房信息模板
  importWaterExcelUrl = "family/water/importExcel",//导入饮水信息
  exampleWaterXlsUrl = "/family/water/example/exportXls",//导出饮水信息模板
  importProductionExcelUrl = "family/production/importExcel",//导入产业信息
  exampleProductionXlsUrl = "/family/production/example/exportXls",//导出产业信息模板
}
/**
 * 导出户详细信息
 * @param params
 */
export const getExportUrl = Api.exportXlsUrl;
/**
 * 导入住房信息
 */
export const getImportUrl = Api.importHouseExcelUrl;
/**
 * 列表接口
 * @param params
 */
export const list = (params) =>
  defHttp.get({ url: Api.list, params });


export const queryByFamilyAndYear = (params) =>
  defHttp.get({ url: Api.queryByFamilyAndYear, params });
/**
 * 删除单个
 */
export const deleteOne = (params, handleSuccess) => {
  return defHttp.delete({ url: Api.deleteOne, params }, { joinParamsToUrl: true }).then(() => {
    handleSuccess();
  });
}
/**
 * 批量删除
 * @param params
 */
export const batchDelete = (params, handleSuccess) => {
  createConfirm({
    iconType: 'warning',
    title: '确认删除',
    content: '是否删除选中数据',
    okText: '确认',
    cancelText: '取消',
    onOk: () => {
      return defHttp.delete({ url: Api.deleteBatch, data: params }, { joinParamsToUrl: true }).then(() => {
        handleSuccess();
      });
    }
  });
}
/**
 * 保存或者更新
 * @param params
 */
export const saveOrUpdate = (params, isUpdate) => {
  // let url = isUpdate ? Api.save : Api.save;
  return defHttp.post({ url:  Api.save, params });
}
