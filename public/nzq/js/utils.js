(function () {
    var utils = {
        base: {
            BASE_URL: '',
        },
        ajax: function (option) {
            var url = this.base.BASE_URL + option.url;
            var fdata = JSON.stringify(option.data);
            var defer = $.Deferred();
            $.ajax({
                url: option.other_url || url,
                data:fdata,
                type:option.type||"POST",
                async:(option.async==null || option.async==undefined?true:false),
                dataType:option.dataType||"json",
                contentType:option.contentType||"application/json",
                beforeSend:function(request){
                
                },
                success:function(res) {
                    if(res.status == '400' || res.status == '401') {
                        defer.reject(res);
                    }
                    defer.resolve(res);
                },
                error:function () {
                    defer.reject();
                }
            });
            return defer.promise();
        },
        tools: {
            getQueryString: function (name, search) {
                search = search || window.location.search.substr(1);
                var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
                var res = reg.match(search);
                if (res != null) {
                    return unescape(res[2]);
                } else {
                    return null;
                }
            }
        }
    };
    window.utils = utils;
})();