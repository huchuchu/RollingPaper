var index = {

    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){ _this.save();});
        $('#btn-update').on('click', function(){ _this.update();});
        $('#btn-delete').on('click', function(){ _this.delete();});
        $('#btn-comment-save').on('click', function(){_this.saveComment();});
        $('#btn-comment-update').on('click', function(){_this.commentUpdate();});
    },
    commentUpdate : function(){

        var commentWriterId = $('#userId').val();
        var sessionUserId = $('#loginId').val();
        var data = {
            comment :  $('#comment-content').val(),
            postId : $('#postsId').val(),
            id : $('#id').val()
        };

        if(commentWriterId !== sessionUserId){
            alert("본인의 댓글만 수정할 수 있습니다");
            return false;
        }

        if(!data.comment || data.comment.trim() === ""){
            alert("공백 또는 입력하지 않은 부분이 있습니다");
            return false;
        }

        const con_check = confirm("수정하시겠습니까?");


        if(con_check == true){

            $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/'+data.postId+'/comment/'+data.id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify(data)
            }).done(function(){
               alert('댓글이 수정되었습니다');
               window.location.reload();
            }).fail(function(error){
               alert(error);
            })

        }

    },
    commentDelete : function(sessionUserId, commentWriterId, postId, id  ){

        if(sessionUserId !== commentWriterId){
            alert('본인이 작성한 댓글만 삭제할 수 있습니다.');
            return false;
        }
            const con_check = confirm("삭제하시겠습니까?");

            var data ={
                id : id,
                postId : postId
            };

            if(con_check== true){

                $.ajax({
                    type : 'DELETE',
                    url : '/api/v1/posts/'+postId+'/comment/'+id,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(){
                    alert("댓글이 삭제되었습니다");
                    window.location.reload();
                }).fail(function(error){
                    alert((error));
                });

            }


    },
    saveComment : function(){
        var data = {
            comment : $('#comment').val(),
            postId : $('#postId').val()
        };

        if(!data.comment || data.comment.trim() ===""){
            alert("공백 또는 입력하지않은 부분이 있습니다");
            return false;
        }

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts/'+data.postId+'/comment',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            console.log('comment success');
            window.location.reload();
        }).fail(function(error){
            if(error.status == 403){
            alert("인증된 사용자만 댓글을 작성할 수 있습니다")
            }else{
            alert(JSON.stringify(error));
            }
        });
    },
    save : function(){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        if(!data.title || data.title.trim() === ""){
            alert("제목을 입력해주세요");
            return false;
        }
        if(!data.content || data.content.trim() === ""){
            alert("내용을 입력해주세요");
            return false;
        }



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
            if(error.status == 403){
            alert("인증된 사용자만 글을 작성할 수 있습니다")
            }else{
            alert(JSON.stringify(error));
            }
        });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();

        if(!data.title || data.title.trim() === ""){
            alert("제목을 입력해주세요");
            return false;
        }

        if(!data.content || data.content.trim() === ""){
            alert("내용을 입력해주세요");
            return false;
        }

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
        var id = $('#postId').val();

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

