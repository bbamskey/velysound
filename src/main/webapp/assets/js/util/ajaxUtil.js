const ajaxUtil = {
	send: function (settingObj, paramData, successCallBack, errorCallBack) {
		let url = settingObj.url;
		let type = settingObj.type || 'POST';
		let dataType = settingObj.dataType || 'json';
		let async = (settingObj.async === "false" || settingObj.async == false) ? false : true;
		let contentType = settingObj.contentType || "application/json; charset=UTF-8";

		if (type == "POST") paramData = JSON.stringify(paramData);

		console.log("►►►► ajaxUtil.send()", {
			url: url,
			type: type,
			data: paramData,
			dataType: dataType,
			async: async,
			contentType: contentType
		})

		$.ajax({
			url: url,
			type: type,
			data: paramData,
			dataType: dataType,
			async: async,
			contentType: contentType,
		})
			.done(function (data) {
				if (successCallBack) {
					successCallBack(data);
				}
			})
			.fail(function (xhr) {
				if (errorCallBack) {
					errorCallBack(xhr, paramData);
				}
			});
	},
	sendFile: function (settingObj, paramData, successCallBack, errorCallBack) {
		let url = settingObj.url;
		let type = settingObj.type || 'POST';
		let async = settingObj.async !== "false" ? true : false;

		$.ajax({
			url: url,
			type: type,
			data: paramData,
			async: async,
			enctype: 'multipart/form-data',
			cache: false,
			contentType: false,
			processData: false,
		})
			.done(function (data) {
				if (successCallBack) {
					successCallBack(data);
				}
			})
			.fail(function (xhr) {
				if (errorCallBack) {
					errorCallBack(xhr, paramData);
				}
			});
	},
}

