const getSheet = {
	list: function (settingObj, paramData, successCallBack, errorCallBack) {
		ajaxUtil.send($.extend({'url': '/sheet/list'}, settingObj), paramData, successCallBack, errorCallBack);
	},
}
