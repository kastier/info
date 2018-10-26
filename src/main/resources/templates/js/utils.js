var $MessageBox={
    success: function(_title,_message,_isinput,_okCallBack,_cancelCallBack){
        $('body').dailog({
            type:'success',
            discription:_message,
            title:_title,
            isInput:_isinput?true:false
        },function(ret){
            if(ret.index==0){
                if(_okCallBack){
                    _okCallBack();
                }
            }else{
                if(_cancelCallBack){
                    _cancelCallBack();
                }
            }
        });
    },
    fail:function(_title,_message){
        $('body').dailog({
            type:'danger',
            title:_title,
            discription:_message
        })
    }
}