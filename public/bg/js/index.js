
layui.use('element', function(){
    var $ = layui.jquery;
    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    FrameWH();
    //触发事件
    var tab_active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd: function(url,id,name) {
            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            element.tabAdd('list_tabs', {
                title: name,
                content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;border: none; min-width: 320px;"></iframe>',
                id: id //规定好的id
            })

            FrameWH();
        },
        tabChange: function(id) {
            //切换到指定Tab项
            element.tabChange('list_tabs', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function (id) {
            element.tabDelete("list_tabs", id);//删除
        }
    };
    //计算ifram高度
    function FrameWH() {
        var h = $(window).height() - 114;
        $(".layui-tab-content .layui-tab-item iframe").css("height",h+"px");
    }
    //屏幕大小变化是调节iframe的高度
    $(window).resize(function () {
        FrameWH();
    });
    //左侧导航点击事件
    $('.site-active').on('click', function(){
        var othis = $(this);
        var url = othis.data('url');
        var id = othis.data('id');
        var name = othis.find('a').html();
        if ($(".layui-tab-title li[lay-id]").length >= 1) {
            var isData = false;
            $.each($(".layui-tab-title li[lay-id]"), function () {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") == id) {
                    isData = true;
                }
            })
            if (isData == false) {
                //标志为false 新增一个tab项
                tab_active.tabAdd(url,id,name);
            }
        }
        else {
        }
        tab_active.tabChange(id);
    });
    //监听选中选项卡
    element.on('tab(list_tabs)', function(data){
        var id =$(this).attr('lay-id');
        $.each($(".lfNav li .site-active[data-id]"), function () {
            //如果点击右侧tab项中的lay-id属性所传入的id 在左侧菜单栏data-id可以找到，则跳转左侧的选中位置
            if ($(this).attr("data-id") == id) {
                $('.lfNav li.list_item dd').removeClass('layui-this');
                $(this).addClass('layui-this');

            }
        })
    });
    $('#logout').on('click', function () {
        $(this).closest("form").submit();
    });
});