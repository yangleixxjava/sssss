(function (jQuery){ 
	
	/**
	 * 请求catalogId
	 */
	this.generateCatalogId = function(url){
		var uploadCatalogId;
		//请求catalogId
		
		$.ajax({ 
		    type: "post", 
		    async: false, 
		    url: url, 
		    dataType: "json", 
		    success: function (data, textStatus, jqXHR){
		    	catalogId = data.catalogId;
		    }
		});
		
		return catalogId;
	}
	
	/**
	 * 上传组件初始化
	 */
	this.start =function(prefs){
		//链接 可以根据需要覆盖
		var uploadUrl = "/fileupload/upload.do";
		var deleteUrl = "/fileupload/delete.do";
		var deleteAllUrl = "/fileupload/deleteByKind.do";
		var downloadUrl = "/fileupload/download.do";
		var listUrl = "/fileupload/listByKind.do";
		
		var contextPath="";
		var moduleId;
		var kind;
		var catalogId;
		var fileSize="1024000";
		var fileTypes="*.*";
		var buttonContainer;
		var fileListContainer;
		var cancelContainer;
		var fileNameWidth=180;
		var showInfo=true;
		var editMode=false;
		var deleteContainer;
		if(prefs){
			if (prefs.contextPath) {
	        	contextPath = prefs.contextPath;
		    };
			if (prefs.moduleId) {
	        	moduleId = prefs.moduleId;
		    };
			if (prefs.kind) {
	        	kind = prefs.kind;
		    };
		    if (prefs.catalogId) {
	        	catalogId = prefs.catalogId;
		    };
		    
			if (prefs.fileSize) {
	        	fileSize = prefs.fileSize;
		    };
			if (prefs.fileTypes) {
	        	fileTypes = prefs.fileTypes;
		    };
			if (prefs.buttonContainer) {
	        	buttonContainer = prefs.buttonContainer;
		    }
			else{
				alert(uncompile(quiLanguage.fileUpload.errorMessage1));
			}
			if (prefs.fileListContainer) {
	        	fileListContainer = prefs.fileListContainer;
		    }
			else{
				alert(uncompile(quiLanguage.fileUpload.errorMessage2));
			}
			if (prefs.deleteContainer) {
	        	deleteContainer = prefs.deleteContainer;
		    };
			if (prefs.fileNameWidth) {
	        	fileNameWidth = prefs.fileNameWidth;
		    };
			if (prefs.showInfo==false) {
	        	showInfo = false;
		    };
			if (prefs.editMode==true) {
	        	editMode = true;
		    };
		    //对于请求url的处理
			if(prefs.uploadUrl){
		    	uploadUrl = contextPath + prefs.uploadUrl;
				if(catalogId&&kind&&moduleId){
					uploadUrl=uploadUrl + "&catalogId=" + catalogId + 
		    	             "&moduleId=" + moduleId + "&kind="+kind;
				}
				else if(catalogId){
					uploadUrl=uploadUrl + "&catalogId=" + catalogId;
				}
				else if(moduleId){
					uploadUrl=uploadUrl + "&moduleId=" + moduleId;
				}
				else if(kind){
					uploadUrl=uploadUrl + "&kind=" + kind;
				}
				else if(catalogId&&kind){
					uploadUrl=uploadUrl + "&catalogId=" + catalogId + "&kind="+kind;
				}
				else if(catalogId&&moduleId){
					uploadUrl=uploadUrl + "&catalogId=" + catalogId + "&moduleId="+moduleId;
				}
				else if(kind&&moduleId){
					uploadUrl=uploadUrl + "&kind=" + kind + "&moduleId="+moduleId;
				}
		    }else{
		    	uploadUrl = contextPath + uploadUrl + "&catalogId=" + catalogId + 
		    	             "&moduleId=" + moduleId + "&kind="+kind;
		    }
		    if(prefs.deleteUrl){
		    	deleteUrl = contextPath + prefs.deleteUrl;
		    }else{
		    	deleteUrl = contextPath + deleteUrl + "&id=";
		    }
		    if(prefs.deleteAllUrl){
		    	deleteAllUrl = contextPath + prefs.deleteAllUrl;
				if(catalogId&&kind){
					deleteAllUrl=deleteAllUrl + "&catalogId=" + catalogId + "&kind=" + kind;
				}
				else if(catalogId){
					deleteAllUrl=deleteAllUrl+"&catalogId=" + catalogId;
				}
				else if(kind){
					deleteAllUrl=deleteAllUrl+"&kind=" + kind;
				}
		    }else{
		    	deleteAllUrl = contextPath + deleteAllUrl + "&catalogId=" + catalogId + "&kind=" + kind;
		    }
		    if(prefs.downloadUrl){
		    	downloadUrl = contextPath + prefs.downloadUrl;
		    }else{
		    	downloadUrl = contextPath + downloadUrl + "&id=";
		    }
		    if(prefs.listUrl){
				listUrl = contextPath + prefs.listUrl;
				if(catalogId&&kind){
					listUrl=listUrl + "&catalogId=" + catalogId + "&kind=" + kind;
				}
				else if(catalogId){
					listUrl=listUrl+"&catalogId=" + catalogId;
				}
				else if(kind){
					listUrl=listUrl+"&kind=" + kind;
				}
		    }else{
		    	listUrl = contextPath + listUrl + "&catalogId=" + catalogId + "&kind=" + kind;
		    }
		}
		 //得到catalogId后初始化上传组件
		 var uploader = new SWFUpload({
		 	catalogId : catalogId,
			//路径
			upload_url: uploadUrl,
			delete_url: deleteUrl,
			delete_all_url: deleteAllUrl,
			download_url: downloadUrl,
			list_url: listUrl,
			// 上传文件最大体积
			file_size_limit : fileSize,	// 100MB
			//文件类型
			file_types : fileTypes,
			//文件类型描述
			file_types_description : uncompile(quiLanguage.fileUpload.typesDescription),

			// 事件处理
			file_dialog_start_handler : fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			// 按钮图片和所在容器
			button_image_url : contextPath + "/libs/js/form/upload/button.jpg",
			button_placeholder_id :buttonContainer,
			
			// 上传flash路径
			flash_url : contextPath+"/libs/js/form/upload/swfupload.swf",

			custom_settings : {
				//文件列表容器
				progressTarget : fileListContainer,
				//文件名长度
				fileNameWidth:fileNameWidth,
				//全部删除按钮id
				cancelButtonId : deleteContainer,
				//是否显示提示
				showInfo:showInfo
			}
		 });
		if(deleteContainer){
			var $deleteAllCon=$("#"+deleteContainer);
			var $deleteAll=$('<span class="icon_delete hand">'+uncompile(quiLanguage.fileUpload.deleteAllText)+'</span>');
			$deleteAllCon.append($deleteAll);
			$deleteAll.click(function(){
				cancelQueue(uploader);
			});
		}	
		
		if(editMode){
			$.fileUpload.addUploadList(uploader);
		}
		return uploader;
	}
	
	
	/**
	 * 查看文件列表（只读）
	 */
	this.addUploadListRead = function(prefs){
	    //链接
		var downloadUrl = "/fileupload/download.do";
		var listUrl = "/fileupload/listByKind.do";
		var contextPath="";
		var kind;
		var catalogId;
		var fileListContainer;
		var fileNameWidth=180;
		var deleteUrl;
		var showInfo=true;
		if(prefs){
			if (prefs.contextPath) {
	        	contextPath = prefs.contextPath;
		    };
			if (prefs.kind) {
	        	kind = prefs.kind;
		    };
		    if (prefs.catalogId) {
	        	catalogId = prefs.catalogId;
		    }
			
			if (prefs.fileListContainer) {
	        	fileListContainer = prefs.fileListContainer;
		    }else{
				alert(uncompile(quiLanguage.fileUpload.errorMessage2))
			}
			
		    //链接的处理
		    if(prefs.downloadUrl){
		    	downloadUrl = contextPath + prefs.downloadUrl;
		    }else{
		    	downloadUrl = contextPath + downloadUrl + "&id=";
		    }
		    if(prefs.listUrl){
		    	listUrl = contextPath + prefs.listUrl;
				if(catalogId&&kind){
					listUrl=listUrl + "&catalogId=" + catalogId + "&kind=" + kind;
				}
				else if(catalogId){
					listUrl=listUrl+"&catalogId=" + catalogId;
				}
				else if(kind){
					listUrl=listUrl+"&kind=" + kind;
				}
				
		    }else{
		    	listUrl = contextPath + listUrl + "&catalogId=" + catalogId + "&kind=" + kind;
		    }
			
			if (prefs.fileNameWidth) {
	        	fileNameWidth = prefs.fileNameWidth;
		    }
			if (prefs.deleteUrl) {
	        	deleteUrl = contextPath + prefs.deleteUrl;
		    };
			if (prefs.showInfo==false) {
	        	showInfo=false;
		    };
		}
		$.getJSON(listUrl, function(data){
			if(!data){
				return;
			}
			var $attachListCon = $("#" + fileListContainer);
			
			$.each(data.attachmentList, function(idx,item){
				var $listItemCon = $('<div class="upload_list"></div>');
				$listItemCon.append($('<div class="upload_iconBg"><div class="upload_icon_ok"></div></div>'));
				
				var $listItemNameCon = $('<div class="upload_name"></div>');
				var $listItemName = $('<span></span>');
				if(showInfo){
					$listItemName.attr("title", item.uploadFileName);
					try{
						addTooltip($listItemName[0]);
					}
					catch(e){}
				}
				
				$listItemName.html(item.uploadFileName);
				$listItemNameCon.append($listItemName);
				$listItemCon.append($listItemNameCon);
				$listItemNameCon.width(fileNameWidth);
				
				var $downloadCon = $('<div class="upload_delete"></div>');
				var $download = $('<a href=\'' + downloadUrl + item.id + '\'>'+uncompile(quiLanguage.fileUpload.downloadText)+'</a>');
				$downloadCon.append($download);
				$listItemCon.append($downloadCon);
				
				if(deleteUrl){
					var $deleteCon = $('<div class="upload_delete"></div>');
					var $delete = $('<a>'+uncompile(quiLanguage.fileUpload.deleteText)+'</a>');
					$deleteCon.append($delete);
					$listItemCon.append($deleteCon);
					$delete.click(function(){
						//删除
						$.post(deleteUrl + item.id, function(data){
							$listItemCon.html("");
							$listItemCon.hide();
						});
					})
				}
				
				$listItemCon.append($('<div class="clear"></div>'));
				$attachListCon.append($listItemCon);
			})
			
		});
	}
	
	
	/**
	 * 文件列表的编辑。展示之前上传的文件列表，且能上传新的文件。
	 */
	this.addUploadList = function(uploader){
		$.getJSON(uploader.settings.list_url, function(data){
			if(!data){
				return;
			}
			var $attachListCon = $('<div class="existUploadList"></div>');
			$("#" + uploader.customSettings.progressTarget).after($attachListCon);
			$attachListCon.attr("targetId",uploader.customSettings.progressTarget);
			
			$.each(data.attachmentList, function(idx,item){
				var $listItemCon = $('<div class="upload_list"></div>');
				$listItemCon.append($('<div class="upload_iconBg"><div class="upload_icon_ok"></div></div>'));
				
				var $listItemNameCon = $('<div class="upload_name"></div>');
				var $listItemName = $('<span></span>');
				if(uploader.customSettings.showInfo){
					$listItemName.attr("title", item.uploadFileName);
					try{
						addTooltip($listItemName[0]);
					}
					catch(e){}
				}
				$listItemName.html(item.uploadFileName);
				$listItemNameCon.append($listItemName);
				$listItemCon.append($listItemNameCon);
				$listItemNameCon.width(uploader.customSettings.fileNameWidth);
				
				var $downloadCon = $('<div class="upload_delete"></div>');
				var $download = $('<a href=\'' + uploader.settings.download_url + item.id + '\'>'+uncompile(quiLanguage.fileUpload.downloadText)+'</a>');
				$downloadCon.append($download);
				$listItemCon.append($downloadCon);
				
				var $deleteCon = $('<div class="upload_delete"></div>');
				var $delete = $('<a>'+uncompile(quiLanguage.fileUpload.deleteText)+'</a>');
				$deleteCon.append($delete);
				$listItemCon.append($deleteCon);
				$delete.click(function(){
					//删除
					$.post(uploader.settings.delete_url + item.id, function(data){
						$listItemCon.html("");
						$listItemCon.hide();
					});
				})
				$listItemCon.append($('<div class="clear"></div>'));
				$attachListCon.append($listItemCon);
			})
			
			
		});
	}
	
	
	jQuery.fileUpload = this;
    return jQuery;
})(jQuery);