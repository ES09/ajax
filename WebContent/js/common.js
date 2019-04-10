/**
 * 
 */
var AjaxUtil = function(){
		var xhr = new XMLHttpRequest();
		this.open = function(url, method, async){
			method = method?method:'GET';
			async = async?async:true;
			xhr.open(method, url);
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						this.callback(xhr.response);
					}
				}
			}
		}
		
		xhr.callback = function(res){
			console.log(res);
		}
		this.setCallback = function(call){
			xhr.callback = call;
		}
		this.send = function(){
			xhr.send();
		}
	}