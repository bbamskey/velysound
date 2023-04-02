/**
 * @FileName 	commonUtil.js
 * @author 		jwkim@mind-one.co.kr
 * @Date 		2022.04.20
 * @Description 공통
 * @History
 * DATE				  AUTHOR					NOTE	
 * -------------------------------------------------
 * 2022.04.20	jwkim@mind-one.co.kr			신규
 * -------------------------------------------------
 */
 $(document).ready(function(){
	//show, hide 이벤트 추가
	$.each(['show', 'hide'], function (i, ev) {
		const el = $.fn[ev];
		$.fn[ev] = function () {
			this.trigger(ev);
			return el.apply(this, arguments);
		};
	});
	
	//정수
	$(document).on("keyup", '.fmt-integer', function () {
		const regex = /[^0-9]/g;
		const v = $(this).val();
		$(this).val(v.replace(regex, ""));
	});
	
	//소수점
	$(document).on("keyup", '.fmt-float', function () {
		const regex =  /(^\d+$)|(^\d{1,}.\d{0,9}$)/;
		const v = $(this).val();
		
		if (!regex.test(v)) {
			$(this).val("");
		};
	});
	 
	commonUtil.init();
});

const commonUtil = {
	init: function(){
		//천단위 콤마 추가
		$('.fmt-thousand').on('keyup onchange', function(){
			const regex = /[^0-9]/g;
			const v = $(this).val();
			
			if(Number.isNaN((commonUtil.removeComma(v)))){
				$(this).val(v.replace(regex, ""));
			}else{
				$(this).val(commonUtil.addComma(v));
			}
		});
		
		$.fn.serializeObject = function() {
			let obj = null;
			try {
				// this[0].tagName이 form tag일 경우
				if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
					var arr = this.serializeArray();
					if(arr){
						obj = {};
						jQuery.each(arr, function() {
							// obj의 key값은 arr의 name, obj의 value는 value값
							obj[this.name] = this.value;
						});
					}
				}
			} catch(e) {
				alert(e.message);
			} finally {}
			
			return obj;
		};
	},
	redirectUrl: function(_url) {
		window.location.href = _url;
	},
	showLoader: function() {
		$(".preloader").fadeIn();
	},
	hideLoader: function() {
		$(".preloader").fadeOut();
	},
	showBackDrop: function() {
		$(".backdrop").show();
	},
	hideBackDrop: function() {
		$(".backdrop").hide();
	},
	ftUpper: function(_str) {
		return _str.charAt(0).toUpperCase() + _str.slice(1);
	},
	getNmPaths: function(clftPath, clftDtlPath) {
		let clftNmPath = [];
		let clftDtlNmPath = [];
		let g_clftDtlCds = [];
		ajaxUtil.send({ 'url': '/assets/getAssetsClftDtl', async: 'false' }, {}, function(result) {
			g_clftDtlCds = result.data;
		});
		for (let i = 0; i < clftDtlPath.length; i++) {
			let clftCd = clftPath[i];
			let clftDtlCd = clftDtlPath[i];
			for (let j = 0; j < g_clftDtlCds.length; j++) {
				if (g_clftDtlCds[j].assetsClftCd === clftCd && g_clftDtlCds[j].assetsClftDtlCd === clftDtlCd) {
					clftNmPath.push(g_clftDtlCds[j].assetsClftNm);
					clftDtlNmPath.push(g_clftDtlCds[j].assetsClftDtlNm);
					break;
				}
			}
		}

		let returnObj = {
			clftNmPath: clftNmPath,
			clftDtlNmPath: clftDtlNmPath
		};
		return returnObj;
	},
	nvl: function(str, defaultVal) {
		let defaultValue = "";

		if (typeof defaultVal != 'undefined') {
			defaultValue = defaultVal;
		}
		if (typeof str === "undefined" || str === null || str === 'null' || str == '' || str === "undefined") {
			return defaultValue;
		}
		return str;
	},
	isNull: function(str) {
		if (typeof str === "undefined" || str === null || str === 'null' || str === '' || str === "undefined") {
			return true;
		}
		return false;
	},
	//숫자 천단위 컴마
	numFmt: function(inputNumber, precision) {
		if (inputNumber == null || inputNumber == 'undefined') {
			return 0;
		}
		let returnNumber;
		precision = precision == null ? 0 : precision;
		if (typeof inputNumber != 'Number') {
			inputNumber = Number(inputNumber).toFixed(precision) + ""
			returnNumber = inputNumber.split(".");
		} else {
			returnNumber = (inputNumber.toFixed(precision) + "").split(".");
		}
		let a = returnNumber[0];

		if (typeof returnNumber[1] == 'undefined') {
			result = a.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		} else {
			result = a.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "." + returnNumber[1];
		}

		return result;
	},
	//소숫점 처리
	numPrecision: function(inputNumber, precision,method) {
		let returnNumber = 0;
		if (inputNumber == null || inputNumber == 'undefined' ) {
			return returnNumber;
		}
		if (isNaN(inputNumber) ) {
			return inputNumber;
		}
		
		if(method=="floor"){
			returnNumber = Math.floor(inputNumber * Math.pow(10, precision)) / Math.pow(10, precision);
		}else if(method=="ceil"){
			returnNumber = Math.ceil(inputNumber * Math.pow(10, precision)) / Math.pow(10, precision);
		}else{
			returnNumber = Math.round(inputNumber * Math.pow(10, precision)) / Math.pow(10, precision);
		}
		return returnNumber;
	},
	addComma: function(n, prefix) {
		if (!n) return n;
	
		let r = this.removeComma(n).toLocaleString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	
		if (prefix) r = prefix + r;
	
		return r;
	},
	removeComma: function(n) {
		if (!n) return;
		return Number(String(n).replace(/,/gi, ""));
	},
	parsetInt: function(n){
		if (!n) return;
		return Number(String(n).replace(/[^0-9]/gi, ""));
	},
	serializeObject: function(id) {
		let result = {};
		$.each($('#' + id).serializeArray(), function(i, element) {
			let node = result[element.name];
			if (typeof node !== 'undefined' && node !== null) {
				if ($.isArray(node)) {
					node.push(element.value);
				} else {
					result[element.name] = [node, element.value];
				}
			} else {
				result[element.name] = element.value;
			}
		});
		$.each($('#' + id).find('input[type=checkbox]'), function(i, element) {
			result[element.name] = $(element).prop('checked');
		});
		return result;
	},
	popupOpen: function(url, title, option) {
		option = option || '';
		option += 'height=' + screen.height + ',width=' + screen.width + ',fullscreen=yes,location=no,titlebar=no';
		return window.open(url, title, option);
	},
	print: function(id, options) {
		$('#' + id).printThis(options);
	},
	average : function (list){
		let avg = 0;
		let sum = 0;
		let size = list.length;
		
		if(size > 0){
			list.map(item => sum += item);
			avg = sum / size;
			return avg;
		} else return 0;
	},
	stdeva : function (list){
		let avg = this.average(list);
		let size = list.length;
		let devTotal = 0;
		if(size > 0){
			list.map(item => {
				let dev =Math.pow( item - avg,2);
				if(!isNaN(dev)) devTotal += dev;
				else devTotal += 0;
			});
			if(!isNaN(devTotal)) return Math.sqrt(devTotal/size-1);
			else return 0;
		}else return 0;
		
	},
	isEmptyObject : function (param){
		return Object.keys(param).length === 0 && param.constructor === Object;
	},
	isEmpty: function(data) {
		if(typeof(data) === 'object') {
			if(!data) {
				return true;
			}
			else if(JSON.stringify(data) === '{}' || JSON.stringify(data) === '[]') {
				return true;
			}
			return false;
		}
		else if(typeof(data) === 'string') {
			if(!data.trim()) {
				return true;
			}
			return false;
		}
		else if(typeof(data) === 'undefined') {
			return true;
		}
		else if(isNaN(data) === true) {
			return true;
		}
		else {
			return false;
		}
	},
	replaceNanZero : function(val) {
		return $.isNumeric(val)?val:0;
	},
	isAlpha : function(str) {
        if (str.search(/[^A-Za-z]/) == -1) return true;
    	else return false;
	},
	onlyAlphabet : function(ele) {
		ele.value = ele.value.replace(/[^\\!-z]/gi,"");
	},
	createObject : function(proto,...params) {
		let obj = Object.create(proto);
		params.forEach((param) => {
			Object.assign(obj, param);
		});
		return obj;
	},
	validateMinMax : function(minValId, maxValId){
		let me = this;
		let prevSttDt=""
		let prevEntDt=""
		$("#"+minValId+", #"+maxValId).on('focus',function(){
			//이전값 저장
			prevSttDt=$("#"+minValId).val();
			prevEntDt=$("#"+maxValId).val();
		}).on('change',function(){
			
			let value = $(this).val();
			if(value) value = value.replace("-", "");
			
			if( $(this).attr("name") == "istYmd" && $.isNumeric(value) && value.length == 6 && Number(calendarUtil.getCurrentYearMonth()) < Number(value) ) {
				$("#"+minValId).val(prevSttDt);
				$("#"+maxValId).val(prevEntDt);
				return alert("설치기간은 현재이후의 기간을 선택할 수 없습니다.");
			} 
			
			me.validOnlyNum([minValId,maxValId]);
			const minObj = $("#"+minValId) != undefined ? $("#"+minValId).val() : $('input[name='+minValId+']').val()
			const maxObj = $("#"+maxValId) != undefined ? $("#"+maxValId).val() : $('input[name='+maxValId+']').val()
			if(typeof minObj==="string"||typeof maxObj==="string"){
				const srchStVal = parseInt(minObj.replaceAll('-',''))!=NaN ? parseInt(minObj.replaceAll('-','')):0;
				const srchEdVal = parseInt(maxObj.replaceAll('-',''))!=NaN ? parseInt(maxObj.replaceAll('-','')):0;
				if(srchEdVal==''||isNaN(srchEdVal)){
					$("#"+maxValId).val(minObj);
				}
				if(srchStVal > srchEdVal){
					$("#"+minValId).val(prevSttDt);
					$("#"+maxValId).val(prevEntDt);
					return alert("범위설정이 잘못 되었습니다.");
				}
			}else{
				if(minObj > maxObj) {
					$("#"+minValId).val(prevSttDt);
					$("#"+maxValId).val(prevEntDt);
					return alert("범위설정이 잘못 되었습니다.");
				}
			}
		});		
	},
	validOnlyNum : function(ids){
		//ID 배열로 받기(여러개)
		if(typeof ids ==="object"){
			for(let i in ids){
				let id = ids[i];
				//한글제한
				$("#"+id).val($("#"+id).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ));
				//영어제한
				$("#"+id).val($("#"+id).val().replace( /[a-z]/gi, '' ));
			}
		}else{
			//ID한개
			//한글제한
			$("#"+ids).val($("#"+ids).val().replace( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g, '' ));
			//영어제한
			$("#"+ids).val($("#"+ids).val().replace( /[a-z]/gi, '' ));
		} 
	},
	//날짜 출력
	date : function(date, separator,defaultVal) {
		defaultVal = defaultVal||"-";
		if (date == null || date == 'undefined') {
			return defaultVal;
		}
		let result = [date.slice(0, 4),separator, date.slice(4,6),separator, date.slice(6)].join('');
		return result;
	},
	//시간까지 출력
	time : function(time, separator,defaultVal){
		let timeseparator = ":";
		let result = "";
		defaultVal = defaultVal||"-";
		if (time == null || time == 'undefined') {
			return defaultVal;
		}
		if(time.length==14){
			result = [time.slice(0, 4),separator, time.slice(4,6),separator, time.slice(6,8),' ', time.slice(8,10), timeseparator, time.slice(10,12)].join('');
			return result;		
		}else if(time.length==8){
			result = [time.slice(0, 4),separator, time.slice(4,6),separator, time.slice(6,8),' ', "00", timeseparator, "00"].join('');
			return result;		
		}
	},
	chgEdit : function(gridId,type){
		let dataGrid = gridUtil.getDataGrid(gridId);
		dataGrid.setEditable(false);
	},
	groupBy : function (data, key) {
		return data.reduce(function (carry, el) {
			var group = el[key];
	
			if (carry[group] === undefined) {
				carry[group] = [];
			}
	
			carry[group].push(el)
			return carry;
		}, {});
	},
	logout: function() {
		sessionStorage.clear();
		window.location.href = "/logout.do";
	},
	getTreeData : function(array) {
		let map = {};
		for (let i = 0; i < array.length; i++) {
		    let obj = array[i];
		    obj.items = [];
		    map[obj.id] = obj;
		    let parent = array[i]['parentId'] || '-';
		    if (!map[parent]) {
		        map[parent] = {
		            items: []
		        };
		    }
		    map[parent].items.push(obj);
		}
		return map['-'].items;
	},
	getTreeDataBook : function(array) {
		if(array.length < 1) return [];
		
	    let map = {};
		for (let i = 0; i < array.length; i++) {
		    let obj = array[i];
		    
		    if(obj.depth == '3' && !obj.bookmark) continue;
		    
		    obj.items = [];
		    map[obj.id] = obj;
		    let parent = array[i]['parentId'] || '-';
		    if (!map[parent]) {
		        map[parent] = {
		            items: []
		        };
		    }
		    map[parent].items.push(obj);
		}
		return map['-'].items;
	},
	getOneToArrayByObjectKey: function(arr, key, value){
		let result = {};
		arr.some((item)=>{
			if(item[key] === value){
				result = item;
			}
		});
		return result;
	},
	getArrayByObjectKey: function(arr, key, value){
		let result = [];
		arr.forEach((item)=>{
			if(item[key] === value){
				result.push(item);
			}
		});
		return result;
	},
	checkCapsLock : function(e) {

	      var myKeyCode=0;
	      var myShiftKey=false;
	      
	      if ( document.getElementById ) {
	            myKeyCode=e.which;
	            myShiftKey=( myKeyCode == 16 ) ? true : false;
	      }
	     
	      if ( ( myKeyCode >= 65 && myKeyCode <= 90 ) && !myShiftKey ) {
	          $('#usrPwd').tooltip('show');
	          setTimeout(function(){$('#usrPwd').tooltip('hide');}, 3000);
	          return false;
	      } else if ( ( myKeyCode >= 97 && myKeyCode <= 122 ) && myShiftKey ) {
	    	  $('#usrPwd').tooltip('show');
	    	  setTimeout(function(){$('#usrPwd').tooltip('hide');}, 3000);
	          return false;
	      }

	},
	sortByKey : function(arr,key) {
		let sortRslt = [...arr];
		try{
			sortRslt.sort((a,b)=>{
				let aIstYmd = a[key];
				let bIstYmd = b[key];
				if(aIstYmd<bIstYmd){
					return -1;
				}
				if(aIstYmd>bIstYmd){
					return 1;
				}
				return 0;
			});
		}catch(e){
			return [];
		}
		return sortRslt;
	},
}
