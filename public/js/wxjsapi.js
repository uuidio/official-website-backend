window.onload=function(){
    //do something
    /*微信JsSdk接口配置*/


    var jsApiParam = document.getElementById("jsApiParam");
    if(jsApiParam==null || jsApiParam=='' || jsApiParam.length<=0) {
        return ;
    }
    var timestamp = jsApiParam.getAttribute("data-timestamp");
    var signature = jsApiParam.getAttribute("data-signature");
    var nonceStr = jsApiParam.getAttribute("data-nonceStr");
    var appId = jsApiParam.getAttribute("data-appId");
    var apis = jsApiParam.getAttribute("data-apis");
    if(apis==null || apis=='' || apis.length<=0) {
        return ;
    }

    var apisArr = apis.split(",");

    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: appId, // 必填，企业号的唯一标识，此处填写企业号corpid
        timestamp:timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: apisArr // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });

}