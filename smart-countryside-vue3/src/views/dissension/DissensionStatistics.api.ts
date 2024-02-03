import {defHttp} from '/@/utils/http/axios';
import { useMessage } from "/@/hooks/web/useMessage";

const { createConfirm } = useMessage();

enum Api {
  list = '/dissension/dissension/list',
  save='/dissension/dissension/add',
  edit='/dissension/dissension/edit',
  deleteOne = '/dissension/dissension/delete',
  deleteBatch = '/dissension/dissension/deleteBatch',
  importExcel = '/dissension/dissension/importExcel',
  exportXls = '/dissension/dissension/exportXls',
  addAssign = '/dissension/dissension/addAssign',
  dissensionUserList = '/dissension/dissension/dissensionUserList',
  dissensionResponsibleUserList = '/dissension/dissension/dissensionResponsible',
  echartsUrl = '/dissension/dissension/getCount'
}
/**
 * 导出api
 * @param params
 */
export const getExportUrl = Api.exportXls;

/**
 * 导入api
 */
export const getImportUrl = Api.importExcel;

/**
 * 查询子表数据
 * @param params
 */
export const dissensionInvolvedUserList = Api.dissensionUserList;
/**
 * 查询子表数据
 * @param params
 */
export const dissensionResponsibleUserList = Api.dissensionResponsibleUserList;
/**
 * 列表接口
 * @param params
 */
export const list = (params) =>
  defHttp.get({url: Api.list, params});

/**
 * 删除单个
 */
export const deleteOne = (params,handleSuccess) => {
  return defHttp.delete({url: Api.deleteOne, params}, {joinParamsToUrl: true}).then(() => {
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
      return defHttp.delete({url: Api.deleteBatch, data: params}, {joinParamsToUrl: true}).then(() => {
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
  let url = isUpdate ? Api.edit : Api.save;
  return defHttp.post({url: url, params});
}

/**
 *  分派人员
 * @param params
 */
export const addAssign = (params) => {
  let url = Api.addAssign;
  return defHttp.post({url: url, params});
}

/**
 *  图表数据
 * @param params
 */
export const echartsData = () => {
  let url = Api.echartsUrl;
  return defHttp.get({url: url});
}
