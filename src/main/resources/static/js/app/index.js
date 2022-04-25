var index = {

    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){ _this.save();});
        $('#btn-update').on('click', function(){ _this.update();});
        $('#btn-delete').on('click', function(){ _this.delete();});
        $('#btn-comment-save').on('click', function(){_this.saveComment();});
    },
    saveComment : function(){
        var data = {
            comment : $('#comment').val(),
            postId : $('#postId').val()
        };

        $ajax({
            type : 'POST',
            url : '/api/v1/comment/'+data.postId+'/comment',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            console.log('comment success');
            window.location.reload();
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    save : function(){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다');
            window.location.href='/';
        }).fail(function(){
            alert(JSON.stringify(error));
        })
    },
    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/';
        }).fail(function(){
            alert(JSON.stringify(error));
        });
    },
    saveOld : function(){
            var editForm = $('#editForm')[0];
            var data = new FormData(editForm);
            console.log(data);
            for (var pair of data.entries()) {
                    console.log(pair[0]+ ', ' + pair[1]);
                }

             $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: '/api/v1/posts',
                data: data,
                processData: false,
                contentType: false,
            }).done(function(){
                alert("글이 등록되었습니다");
                window.location.href="/";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });

        }


};

index.init();

