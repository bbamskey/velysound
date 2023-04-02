/**
 * @FileName 	calendarUtil.js
 * @author 		jwkim@mind-one.co.kr
 * @Date 		2022.04.28
 * @Description 달력공통
 * @History
 * DATE				  AUTHOR					NOTE	
 * -------------------------------------------------
 * 2022.04.27	jwkim@mind-one.co.kr			신규
 * -------------------------------------------------
 */

const calendarUtil = {
	getStartYear : function() {
		return 1900;
	},
	getFinalYear : function() {
		var d = new Date();
		var y = d.getFullYear();
		return finalYear = y + 10;
	},
	getCurrentYear : function() {
		return moment().format("YYYY");
	},
	getStartYearMonth : function() {
		return 190001;
	},
	getFinalYearMonth : function() {
		var d = new Date();
		var y = d.getFullYear();
		var m = d.getMonth()+1;
		return y + 10 + "" + m;
	},
	getCurrentYearMonth : function() {
		return moment().format("YYYYMM");
	},
	setIcon : function(id) {
		let $id = $("#"+id);
		$id.siblings('.input-group-append').remove();
		let iconHtml = '<span class="input-group-append bg-white">';
		iconHtml += '<button class="btn border-left-0" type="button" style="border: 1px solid #399AF4;cursor: default;">';
		iconHtml += '<i class="ti-calendar"></i>';
		iconHtml += '</button>';
		iconHtml += '</span>';
		$id.after(iconHtml);
	},
	/**
	 * 일 설정
	 * @param id 아아디
	 * @param minDate 시작일자
	 * @param maxDate 종료일자
	 * @param value 초기값 (today / -nD: n일전 / +nD: n일후 / -nM: n달전 / +nM: n달후 / -nY: n년전 / +nY: n년후)
	 */
	dateInit : function(id,value,minDate,maxDate) {// 일 선택 달력
		if(!minDate){
			minDate = moment().subtract(+100, 'y').format("YYYY-MM-DD");
		}
		if(!maxDate){
			maxDate = moment().subtract(-10, 'y').format("YYYY-MM-DD");
		}
		$("#"+id).datepicker({
			// inline: true,
			dateFormat : "yy-mm-dd", /* 날짜 포맷 */
			prevText : '이전달',
			nextText : '다을달',
			showButtonPanel : true, /* 하단 오늘 버튼 패널 사용 */
			// changeMonth: true, /* 월 선택박스 사용 */
			changeYear : true, /* 년 선택박스 사용 */
			showOtherMonths : true, /* 이전/다음 달 일수 보이기 */
			selectOtherMonths : true, /* 이전/다음 달 일 선택하기 */
			showOn : "both",
			//buttonImage : "/asset/img/input_calendar.png",
			//buttonImageOnly : true,
			minDate : minDate,
			maxDate : maxDate,
			closeText : '닫기',
			currentText : '오늘',
			showMonthAfterYear : true, /* 년과 달의 위치 바꾸기 */
			/* 한글화 */
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
			dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일' ],
			dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			onClose : function(selectedDate) {
			}
		});
		if(value){
			$("#"+id).datepicker('setDate', value);
		}
		$("#"+id).attr('autocomplete','off');
		$(".ui-datepicker-trigger").hide();
		this.setIcon(id);
	},
	/**
	 * 월 설정
	 * @param id 아아디
	 * @param value 초기값 (today / -nD: n일전 / +nD: n일후 / -nM: n달전 / +nM: n달후 / -nY: n년전 / +nY: n년후)
	 */
	monthInit : function(id, value, isOld) {// 월 선택 달력
		let now = new Date();
		let nYear = now.getFullYear();
		let startYear = nYear - 40;
		let endYear = nYear + 10;
		if(value){
			$("#"+id).val(value);
		}
		
		$("#"+id).monthpicker({
			pattern : 'yyyy-mm',
			startYear : startYear,
			finalYear : endYear,
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ]
		});
		$("#"+id).attr('autocomplete','off');
		if(!isOld) this.setIcon(id);
	},
	/**
	 * 년 설정
	 * @param id DOM 아아디
	 * @param option 1. type이 object가 아닐 경우 : 년도
	 * @param option 2. type이 object일 경우 : {start:처음년도, end:마지막년도, default: 초기년도}
	 * @example calendarUtil.year("yearpicker", "2023")
	 * @example calendarUtil.year("yearpicker", {start:"2000", end:"2030", default:"2020"})
	 */
	yearInit : function(id, option, clickFunc) {// 년 선택 달력
		const me = this;
		
		let year = me.getCurrentYear();
		let startYear = me.getStartYear();
		let endYear = me.getFinalYear();
		if(typeof(option) === "object"){
			if(option.default) year = Number(option.default);
			if(option.start) startYear = Number(option.start);
			if(option.end) endYear = Number(option.end);
		}else{
			year = Number(option);
		}
		
		if(option.default) $("#"+id).val(year);
		
		$("#"+id).yearpicker({
			year,
			startYear,
			endYear,
			clickFunc
		});
		
		$("#"+id).attr('autocomplete','off');
	},
	calcDiff : function (compId1, compId2){
		const srchStVal = $('#' + compId1).val().replaceAll('-','');
		const srchEdVal = $('#' + compId2).val().replaceAll('-','');
		const length = srchStVal.length.toString();
		let flag = true;
		if(srchStVal > srchEdVal){
			flag = false;
			switch (length) {
				case '4' :
					returnVal = moment(srchEdVal,'YYYY').subtract(1,'years').format('YYYY');
					$('#' + compId1).val(returnVal);
				break;
				case '6' :
					returnVal = moment(srchEdVal,'YYYYMM').subtract(1,'months').format('YYYY-MM');
					$('#'+compId1)
					.clone()
					.attr('id', compId1+'New')
					.insertAfter('[id='+compId1+']:last');
					
					$('#'+compId1).remove();
					$('#'+compId1+'New').attr('id',compId1);
					this.monthInit(compId1,returnVal,true);
				break;
				case '8' :
					returnVal = moment(srchEdVal,'YYYYMMDD').subtract(1,'days').format('YYYY-MM-DD');
					$("#"+compId1).datepicker('setDate', value);
				break;
			}
		}
		return flag;
	},
	stringToDate: function(date, sparator){
		const yyyyMMdd = String(date).replace(/[-|\.]/gi, '');
		const sYear = yyyyMMdd.substring(0,4);
		const sMonth = yyyyMMdd.substring(4,6);
		const sDate = yyyyMMdd.substring(6,8);
	
		return sYear + sparator + sMonth + sparator + sDate;
	},
	setMonthZero : function(m){
		return ("0" + m).slice(-2);
	},
	drSingleTimePicker : function(id,minDate,maxDate,searchDate){
		$("#"+id).daterangepicker({
			timePicker: true,
			timePickerIncrement: 1,
			timePicker24Hour: true,
			singleDatePicker:true,
			showDropdowns:true,
			format:'YYYY/MM/DD HH:mm',
			minDate: minDate,
			maxDate: maxDate,
			buttonClasses: ['btn btn-default'],
			applyClass: 'btn-sm btn-primary',
			cancelClass: 'btn-sm',
			locale:{
				applyLabel :"선택",
				cancelLabel:"취소",
				daysOfWeek:['일','월','화','수','목','금','토'],
				monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				firstDay:0
			}
		}, function (start, end, label) {
			//console.log(start.toISOString(), end.toISOString(), label);
		});
		if(searchDate != null ){
			setDateParam(id,searchDate);
		}
		$(id).attr('autocomplete','off');
	},
	setDateParam : function(id, date){
		$("#"+id).data('daterangepicker').setStartDate(date);
		$("#"+id).data('daterangepicker').setEndDate(date);
	},
	getLastDay: function(yyyymm){
		if(commonUtil.isNull(yyyymm)) return;
		
		const date = yyyymm.replace(/-/gi, "").replace(/\./gi, "").replace(/\//gi, "");
		return new Date(date.substring(0, 4), date.substring(4), 0).getDate();
	}
}

