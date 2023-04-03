$(function () {
	mainJs.init();
});

const mainJs = {
	init: function () {
		const me = this;

		me.getVideos("M1"); //V:비디오, S:악보, M1:메인비디오, M2:메인악보1, M3:메인악보2

		me.getSheets("M2", "mainSheet1Ul");

		me.getSheets("M3", "mainSheet2Ul");

	},
	getVideos: function (displayType) {
		let paramData = {
			displayType,
			paging: {start: 0, size: 8},
		};
		const list = getVideo.list({dataType: 'html'}, paramData, function (result) {
			$('#mainVideoUl').html(result);
		});
	},
	getSheets: function (displayType, appendId) {
		let paramData = {
			displayType,
		};
		const list = getSheet.list({dataType: 'html'}, paramData, function (result) {
			$(`#${appendId}`).html(result);
			setTimeout(() => {
				thumbListSwiperCont.forEach((item) => {
					item.slideTo(0);
				});
			}, 0);
		});
	}
}