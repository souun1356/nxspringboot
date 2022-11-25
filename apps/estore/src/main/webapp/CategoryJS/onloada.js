/**
 * 
 */
function select_onelevel(){
	var ss = document.getElementById("onelevel");
	var onelevelcode = ss.value;
	if(!onelevelcode)return; // 聲明變量就要判斷是否為null
	$.ajax({
		type:"post",//請求方式get/post
		url:"LinkageServlet",//請求對應的地址
		data:{"levelcode":onelevelcode},//往服務器傳遞的參數，
		success:function(data){//服務器交互成功調用的回調函數，data就是服務器端傳遞出來的數據
			var jdata = data.trim(); // 去前後空格
			if(jdata=="fail"){
				alert("查詢失敗!");
			}else{
				var json = eval(jdata);//將json字符串對像轉化成json
				var sel = document.getElementById('twolevel');
				if(json=='null'){
					sel.options.length = 1;
				}else{
					sel.options.length = 0;
					
					for(var i=0; i<json.length; i++) {
						sel.add(new Option(json[i].name,json[i].code));
					}
					showthree(json[0].code);
				}
			}
		}
	});
};
function selsct_twolevel(){
	var sa = document.getElementById("twolevel");
	var twolevelcode = sa.value;
	if(!twolevelcode)return; // 聲明變量就要判斷是否為null
	showthree(twolevelcode);
}
function showthree(code) {
	$.ajax({
		type:"post",//請求方式get/post
		url:"LinkageServlet",//請求對應的地址
		data:{"levelcode":code},//往服務器傳遞的參數，
		success:function(data){//服務器交互成功調用的回調函數，data就是服務器端傳遞出來的數據
			var jdata = data.trim(); // 去前後空格
			if(jdata=="fail"){
				alert("查詢失敗!");
			}else{
				var json = eval(jdata);//將json字符串對像轉化成json
				var sel = document.getElementById('threelevel');
				if(json=='null'){
					sel.options.length = 1;
				}else{
					sel.options.length = 0;
					for(var i=0; i<json.length; i++) {
						sel.add(new Option(json[i].name,json[i].code));
					}
				}
			}
		}
	});
}
function selsct(lcode,obj){
	$.ajax({
		type:"post",//請求方式get/post
		url:"LinkageServlet",//請求對應的地址
		data:{"levelcode":lcode},//往服務器傳遞的參數，
		success:function(data){
			var jdata = data.trim(); // 去前後空格
			if(jdata=="fail"){
				alert("查詢失敗!");
			}else{
				var json = eval(jdata);//將json字符串對像轉化成json
				var dd = "";
				for(var i=0; i<json.length; i++) {
					dd+="<dd onclick='showck("+json[i].code+")'>"+json[i].name+"</dd>";
				}
				document.getElementById(obj).innerHTML=dd;
			}
		}
	});
}