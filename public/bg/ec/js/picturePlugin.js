layui.use(['jquery','upload','laypage','layer'],function(){
    var $=layui.$;
    var layer=layui.layer;
    var laypage = layui.laypage;
    var upload = layui.upload;


    ;(function($, window, document,undefined) {

        // 解决CSRF问题
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        var i=0;
        var Picture = function(ele, opt) {
            this.$element = ele,
                this.defaults = {
                    'popupElement': '.picture-popup',
                    'group': 1,
                    'page': 1,
                    'limit': 8,
                    'initPageFirst': true,
                    'pictureDataUrl': '/bg/picture/r/all.json',
                    'uploadUrl': '/bg/picture/c/upload'
                },
                this.options = $.extend({}, this.defaults, opt),
            this.$pluginHtml = 'picturePlugin_',
            this.$popUpClass = '.mark_img';
        }
        Picture.prototype = {
            init: function () {
                this.initPluginHtml();

                this.bindClickEven();
                this.initGoupEven();
                this.initUpload();
                this.initCloseEven();
                this.initSelectPictureEven();
                this.initSubmitEven();
            },
            bindClickEven: function () {
                var _this = this;
                $(this.$element).off("click").on("click", function () {
                    _this.options.group = 1;
                    function success(result) {
                        var groupingList = result.pictureGroupingList;
                        var pictureList = result.pictureList;
                        var total = result.total;

                        _this.addPictureHtml(pictureList);
                        _this.addGroupHtml(groupingList);
                        _this.initPageBar(total);

                        _this.popup();
                    }
                    _this.getGoupAndPicutreData(_this.options.page,_this.options.limit,_this.options.group, success);

                });
            },initPicture:function (page,limit,group) {
                var _this = this;
                function success(result) {
                    $(_this.options.popupElement).find('#img_items').html('');
                    _this.addPictureHtml(result.data);
                    _this.initPageFirst = true;
                    _this.initPageBar(result.data.totalPages);
                }
                _this.getPictureData(page, limit,group,success);
            },
            addPictureHtml:function (pictureList) {

                var arr = []
                layui.each(pictureList, function (index, item) {
                    // arr.push('<li>'+ item +'</li>');
                    arr.push("<li><div class='img' data-imgurl='" + item.imagePath + "' data-id='"+item.id+"'><img src=" + item.imagePath + "/></div><p>" + item.newName + "</p></li>")
                });
                $(this.$pluginHtml).find('.img_items').html(arr);

            },
            addGroupHtml:function (groupingList) {
                var groupingHtml = '';
                layui.each(groupingList, function (index, item) {
                    if (index == 0) {
                        groupingHtml +=
                            '  <li class ="on" data-id=' + item.id + '>' +
                            '    <span>' + item.pictureOwnership + '</span>' +
                            '<span class=""></span>' +
                            '   </li>';
                    } else {
                        groupingHtml +=
                            '  <li data-id=' + item.id + '>' +
                            '    <span>' + item.pictureOwnership + '</span>' +
                            '    <span class="num"></span>' +
                            '   </li>';
                    }
                });
                $(this.$pluginHtml).find('.mark_left ul').html(groupingHtml);
            },
            getPictureData:function (page,limit,group,callback) {
                var _this =this;
                if(page==null || page.length<=0) {
                    page = _this.options.page;
                }

                if(limit==null || limit.length<=0) {
                    limit = _this.options.limit;
                }
                var url = _this.options.pictureDataUrl+"?page="+page+"&limit="+limit;
                if(group==null) {
                    group = _this.options.group;
                }

                if(group!=null) {
                    url = url + "&pictureGroupingId=" + group;
                }
                $.ajax({
                    type: 'get',
                    url: url,
                    dateType: 'json',
                    success: function (result) {
                        if ( callback != undefined && callback != null && typeof callback == "function" ){
                            callback(result);
                        }
                    }
                });

            },
            getGoupAndPicutreData:function (page,limit,group,callback) {
                var _this =this;
                var url = '/bg/picture/r/pictureOrGrouping?page='+page+'&limit='+limit+'&pictureGroupingId='+group;
                $.ajax({
                    type: 'get',
                    url: url,
                    dateType: 'json',
                    success: function (result) {
                        if ( callback != undefined && callback != null && typeof callback == "function" ){
                            callback(result);
                        }
                    }
                });

            },
            initPageBar:function (total) {
                var _this = this;
                laypage.render({
                    elem: $(_this.$pluginHtml).find(".page")
                    ,count:total
                    ,layout: ['count', 'prev', 'page', 'next', 'skip']
                    ,limit:_this.options.limit
                    ,groups:3
                    ,jump: function(obj){
                        if(_this.initPageFirst) {
                            _this.initPageFirst = false;
                            return;
                        }

                        var page = obj.curr;
                        function pictureSuccess(result) {
                            var  pictureList = result.data;
                            _this.addPictureHtml(pictureList);

                        }
                        _this.getPictureData(page, _this.options.limit,null, pictureSuccess);
                    }
                });
            },
            popup: function () {
                $(this.$pluginHtml).find(this.$popUpClass).show();
            },
            initCloseEven:function () {
                var _this = this;
                $(this.$pluginHtml).find(this.$popUpClass).find('.mark_head .close').click(function () {
                    _this.close();
                });
            },
            close: function () {
                var _this = this;
                $(this.$pluginHtml).find(this.$popUpClass).hide();
            },initUpload:function () {
                var _this = this;
                upload.render({
                    elem:  $(_this.$pluginHtml).find(".up_img")
                    ,url: _this.options.uploadUrl
                    ,multiple: true
                    ,before: function(obj){
                        //预读本地文件示例，不支持ie8
                        this.url = _this.options.uploadUrl+'/' + _this.options.group;
                    }
                    ,done: function(res){
                        if(res == 3) {
                            layer.msg('上传失败');
                        }else {
                            layer.msg('上传成功');
                        }
                        $(_this.$pluginHtml).find(_this.$popUpClass).find('.mark_left ul').find("li.on").click();

                    }

                });
            },initGoupEven:function () {
                var _this = this;
                $(this.$pluginHtml).find('.mark_left').on('click','ul li',function () {
                    $(this).addClass('on').siblings().removeClass('on');
                    _this.options.group = $(this).data("id");
                    _this.initPicture(_this.options.page, _this.options.limit, _this.options.group);
                })
            },
            initSelectPictureEven:function () {
                $(this.$pluginHtml).find('.img_items').on('click','li div',function () {
                    if($(this).parent().hasClass("img_on"))
                    {
                        $(this).parent().removeClass("img_on");
                    }
                    else {
                        $(this).parent().addClass('img_on');
                    }

                })
            },initSubmitEven:function () {
                var _this = this;
                $(this.$pluginHtml).find('.popup-submit').on('click',function () {
                    var callback = _this.options.callback;
                    if ( callback != undefined && callback != null && typeof callback == "function" ){
                        var pictures=[];
                        $(_this.$pluginHtml).find('.img_items').find("li.img_on").each(function (index) {
                            var pic ={};
                            var div = $(this).find(".img");
                            pic.id = div.data("id");
                            pic.url = div.data("imgurl");
                            pictures.push(pic);
                        });

                        callback(pictures);
                        _this.close();
                    }
                });

            },
            initPluginHtml:function () {
                    $(document.body).append('<div class="picturePlugin" id="picturePlugin_'+i+'"><div class="mark_img">'+$(this.options.popupElement).html()+'</div></div>');
                this.$pluginHtml = "#picturePlugin_" + i;
                i = i+1;
            }
        }
        $.fn.picturePlugin = function(options) {
            var picture = new Picture(this, options);
            return picture.init();
        }
    })($, window, document);

});