/**
 * 调用后台批量删除方法
 */
function deleteBatch(bathPath) {
	$("#mainForm").attr("action",bathPath + "deleteall.do");//设置form的action为新的请求
	$("#mainForm").submit();//提交表单
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}
/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}