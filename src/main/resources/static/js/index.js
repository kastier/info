// JavaScript Document
$(document).ready(function(){

//以下是最新公告的代码
    var time2 = setInterval(gong,3000);
    var y=0;
    var len2 = $(".ggao ul li").length;
    var clone2 = $(".ggao ul li").first().clone();
    $(".ggao ul").append(clone2);

    function gong(){
        y++;
        if(y>len2){
            y =1;
            $(".ggao ul").css("top",0);
            $(".ggao ul").animate({top:-y*25});
        }
        $(".ggao ul").animate({top:-y*25});
    }





});



function Progress_(data) {
    this.gObj = {};
    this.$box_ = data.$box_;
    this.maxWidth_ = data.maxWidth_ || "4rem";
    this.leftPercent = data.percent || 0;
}
Progress_.prototype = {
    constructor: Progress_,
    init: function() {
        this.$box_.html('<div id="box_"><div id="bg_"><div id="bgcolor_"></div></div><div id="bt_"></div></div>');
        this.initStyle();
        this.touchstart();
        this.touchmove();
        this.touchend();
        this.mousedown();
        this.mousemove();
        this.mouseup();
        this.bgclick();
    },
    initStyle: function() {
        var that = this;
        that.maxWidth_.indexOf('rem')!=-1 ? that.gObj.left = this.maxWidth_.replace('rem','')*that.leftPercent+'rem' : that.gObj.left = this.maxWidth_*that.leftPercent;
        $('#box_').css({'position': 'relative','width':'100%','margin':'0.2rem','border': '1px solid transparent'});
        $('#bg_').css({'height': '0.4rem','margin-top': '0.14666667rem','border': '1px solid #ddd','border-radius': '5px','overflow': 'hidden','position': 'relative'})
        $('#bgcolor_').css({'background': '#fa5551','width': that.gObj.left,'height': '0.4rem','border-radius': '5px'})
        $('#bt_').css({'width': '0.4rem','height': '0.66666667rem','background-color': '#f3f3f3','border-radius': '0.4rem','overflow': 'hidden','position': 'absolute','left': that.gObj.left,'margin-left': '-0.2rem','border': '1px solid #d0cbcb','top': '0.02rem','cursor': 'pointer'})
        $('#text_xa_').css({'padding': '0 0.26666667rem','margin': '0 auto','font-size':' 0.32rem','padding-left': '0.13333333rem','padding-top':' 0.02666667rem','line-height': '2em'})
        $('#text_xa').css({'padding': '0 0.26666667rem','margin': '0 auto','font-size': '0.32rem','padding-left': '0.13333333rem','padding-top': '0.02666667rem','line-height': '2em'})
    },
    touchstart: function(){
        this.gObj.maxWidth = $('#box_').width();
        var that = this;
        $('#bt_').get(0).addEventListener('touchstart', function(e) {
            that.gObj.left =$('#bgcolor_').width();
            that.gObj.ox = e.touches[0].clientX - that.gObj.left;
            that.gObj.status = true;
        })
    },
    touchmove: function(){
        var that = this;
        $('#bt_').get(0).addEventListener('touchmove', function(e) {
            if(that.gObj.status) {
                that.gObj.left = e.touches[0].clientX - that.gObj.ox;
                that.gObj.left < 0 ? that.gObj.left = 0 : that.gObj.left;
                that.gObj.left > that.gObj.maxWidth ? that.gObj.left = that.gObj.maxWidth : that.gObj.left;
                $('#bt_').css('left', that.gObj.left);
                $('#bgcolor_').width(that.gObj.left);
                var percent1 = parseFloat(that.gObj.left*100 /( that.gObj.maxWidth)).toFixed(2) + '%';
                $('#text_xa_').html('left:' + percent1);
                $('#text_xa').html('right:' + parseFloat((that.gObj.maxWidth-that.gObj.left)*100 /( that.gObj.maxWidth)).toFixed(2) + '%');
            }
        })
    },
    touchend: function(){
        var that = this;
        $('#bt_').get(0).addEventListener('touchend', function(e) {
            that.gObj.status = false;
        })
    },
    mousedown: function(){
        var that = this;
        $('#bt_').mousedown(function(e) {
            that.gObj.left =$('#bgcolor_').width();
            that.gObj.ox = e.pageX- that.gObj.left;
            that.gObj.status = true;
        })
    },
    mousemove: function(){
        var that = this;
        $(document).mousemove(function(e) {
            if(that.gObj.status) {
                that.gObj.left = e.pageX - that.gObj.ox;
                that.gObj.left < 0 ? that.gObj.left = 0 : that.gObj.left;
                that.gObj.left > that.gObj.maxWidth ? that.gObj.left = that.gObj.maxWidth : that.gObj.left;
                $('#bt_').css('left', that.gObj.left);
                $('#bgcolor_').width(that.gObj.left);
                $('#text_xa_').html('left:' + parseFloat(that.gObj.left*100 /( that.gObj.maxWidth)).toFixed(2) + '%');
                $('#text_xa').html('right:' + parseFloat((that.gObj.maxWidth-that.gObj.left)*100 /( that.gObj.maxWidth)).toFixed(2) + '%');
            }
        })
    },
    mouseup: function(){
        var that = this;
        $(document).mouseup(function(e) {
            that.gObj.status = false;
        })
    },
    bgclick: function(){
        var that = this;
        $('#bg_').click(function(e){
            if(!that.gObj.status) {
                var bgleft = $("#bg_").offset().left;
                that.gObj.left = e.pageX - bgleft;
                that.gObj.left < 0 ? that.gObj.left = 0 : that.gObj.left;
                that.gObj.left > that.gObj.maxWidth ? that.gObj.left = that.gObj.maxWidth : that.gObj.left;
                $("#bt_").css('left', that.gObj.left);
                $("#bgcolor_").stop().animate({ width: that.gObj.left }, 200);
                var percent1 = parseFloat(that.gObj.left*100 /( that.gObj.maxWidth)).toFixed(2) + '%';
                $('#text_xa_').html('left:' + percent1);
                $('#text_xa').html('right:' + parseFloat((that.gObj.maxWidth-that.gObj.left)*100 /( that.gObj.maxWidth)).toFixed(2) + '%');
            }
        })

    }
}