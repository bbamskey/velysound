const getVideo = {
	list: function (settingObj, paramData, successCallBack, errorCallBack) {
		ajaxUtil.send($.extend({'url': '/video/list'}, settingObj), paramData, successCallBack, errorCallBack);
	},
}

const postVideo = {
	registVideo: function (settingObj, paramData, successCallBack, errorCallBack) {
		ajaxUtil.send($.extend({'url': '/investFinancial/lcc/getLccAssets'}, settingObj), paramData, successCallBack, errorCallBack);
	},
}