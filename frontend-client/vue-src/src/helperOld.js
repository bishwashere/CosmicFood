module.exports = class Helper {

    userRole = {
        user:'CUSTOMER',
        driver:'DRIVER',
        restaurant:'RESTAURANT'
    }
    
    getUserInfo () {
            var userInfo = {
                username: '',
                firstName: '',
                lastName: '',
                email: '',
                role: '',
                profilePic: '',
            };
            var userInfoLocal = JSON.parse(localStorage.getItem('userInfo'));
            if (typeof (userInfoLocal) === 'object') {
                if (userInfoLocal !== null) {
                    if (typeof (userInfo.username) !== 'undefined') {
                        if (userInfoLocal.username !== '') {
                            userInfo.username = userInfoLocal.username;
                        }
                    }
                    if (typeof (userInfo.firstName) !== 'undefined') {
                        if (userInfoLocal.firstName !== '') {
                            userInfo.firstName = userInfoLocal.firstName;
                        }
                    }
                    if (typeof (userInfo.lastName) !== 'undefined') {
                        if (userInfoLocal.lastName !== '') {
                            userInfo.lastName = userInfoLocal.lastName;
                        }
                    }
                    if (typeof (userInfo.email) !== 'undefined') {
                        if (userInfoLocal.email !== '') {
                            userInfo.email = userInfoLocal.email;
                        }
                    }
                    if (typeof (userInfo.role) !== 'undefined') {
                        if (userInfoLocal.role !== '') {
                            userInfo.role = userInfoLocal.role;
                        }
                    }
                    if (typeof (userInfo.profilePic) !== 'undefined') {
                        if (userInfoLocal.profilePic !== '') {
                            userInfo.profilePic = userInfoLocal.profilePic;
                        }
                    }
                }

            }
            return userInfo;
    }
    setUserNewToken = function (newToken) {
        var userInfo = this.getUserInfo();
        if (userInfo.identity != '') {
            var userInfoLocal = JSON.parse(localStorage.getItem('userInfo'));
            userInfoLocal.username = newToken;
            this.setUserInfo(userInfoLocal);
        }
    }
    unsetUserInfo() {
        localStorage.removeItem('userInfo');
    }
    setUserInfo(datas) {  
        localStorage.setItem('userInfo', JSON.stringify(datas));
    }
    getFormData(formData){

        // formData.append('identity',this.getUserInfo().identity);
        // formData.append('username',this.getUserInfo().username);

        return formData;
    }
    request(params) {

            if (typeof (params) === 'undefined') {
                alert('Empty parameter found.');
            } else {

                var formData = new FormData();
                if (typeof (params.data) !== 'undefined') {
                    formData = this.getFormData(params.data);
                }
                               

                if (typeof (params.url) === 'undefined') {
                    alert('Api is missing');
                } else {
                    if (typeof(params.beforeSend) == 'function') {
                        params.beforeSend();
                    }
                    var method = 'POST';
                    if (typeof (params.type) !== 'undefined') {
                        method = params.type;
                    }
                    params.type = method;
                    if (typeof(params.success) == 'function') {//start

                        if (window.$) {

                            if(typeof(params.withData) != 'undefined'){
                                if(params.withData == 'json'){
                                    var object = {};
                                    formData.forEach(function(value, key){
                                        object[key] = value;
                                    });
                                    params.data = object
                                    this.do_request(params);
                                }else{
                                    this.do_requestFormData(params);
                                }
                            }else{
                                this.do_requestFormData(params);
                            }

                            
                        } else {
                            console.log('jquery not loaded');
                        }

                    }//end      

                }
            }
    }

    do_requestFormData(params){
                        window.$.ajax({
                                dataType: 'json',
                                headers: {
                                    'Authorization':'Bearer '+this.getUserInfo().username,
                                },
                                url: params.url,
                                type: params.type,
                                cache: false,
                                contentType: false,
                                processData: false,
                                data: params.data,
                                xhr: function () {
                                    var myXhr = window.$.ajaxSettings.xhr();
                                    if (typeof(params.uploadProgress) == 'function') {
                                        if (myXhr.upload) {
                                            myXhr.upload.addEventListener('progress', function (e) {
                                                var uploaded = e.loaded;
                                                var total_size = e.total;
                                                var percentage = (uploaded / total_size) * 100;
                                                if (percentage > 100) {
                                                    percentage = 100;
                                                }
                                                var return_datas = {
                                                    uploaded: uploaded,
                                                    total_size: total_size,
                                                    uploading_percent: percentage,
                                                };
                                                params.uploadProgress(return_datas);
                                            }, false);
                                        }
                                    }
                                    return myXhr;
                                },
                                complete:  (resp) =>{
                                    if (typeof(params.complete) == 'function') {
                                        if(resp.status == 403){//credials
                                            params.complete(resp);
                                        }else if(resp.status == 404){
                                            this.showMessage('danger','Record not found.');
                                        }else if(resp.status == 400){
                                            this.showMessage('danger','Bad Request found.');
                                        }else if(resp.status == 500){
                                            this.showMessage('danger','Internal Server Error.');
                                        }
                                        
                                    }else{
                                        if(resp.status == 403){//credials
                                            this.showMessage('danger','Username or password is not matched.');
                                        }else if(resp.status == 404){
                                            this.showMessage('danger','Record not found.');
                                        }else if(resp.status == 400){
                                            this.showMessage('danger','Bad Request found.');
                                        }
                                    }
                                    
                                },
                                success:  (resp) =>{
                                    if (typeof(params.success) == 'function') {
                                        params.success(resp);
                                    }
                                }
                                
                        })
    }
    do_request(params){
        console.log(params);

                    window.$.ajax({
                                dataType: 'json',
                                url: params.url,
                                headers: {
                                    'Authorization':'Bearer '+this.getUserInfo().username,
                                },
                                contentType: 'application/json',
                                type: params.type,
                                data: (JSON.stringify(params.data) == '{}'?{}:JSON.stringify(params.data)),
                                complete:  (resp) =>{
                                    if (typeof(params.complete) == 'function') {
                                        if(resp.status == 403){//credials
                                            params.complete(resp);
                                        }else if(resp.status == 404){
                                            this.showMessage('danger','Record not found.');
                                        }else if(resp.status == 400){
                                            this.showMessage('danger','Bad Request found.');
                                        }else if(resp.status == 500){
                                            this.showMessage('danger','Internal Server Error.');
                                        }
                                        
                                    }else{
                                        if(resp.status == 403){//credials
                                            this.showMessage('danger','Username or password is not matched.');
                                        }else if(resp.status == 404){
                                            this.showMessage('danger','Record not found.');
                                        }else if(resp.status == 400){
                                            this.showMessage('danger','Bad Request found.');
                                        }
                                    }
                                    
                                },
                                success:  (resp) =>{


                                    if (typeof(params.success) == 'function') {
                                        params.success(resp);
                                    }
                                }
                        })
    }

    scroll_to_bottom(selector) {
        var objDiv = document.querySelector(selector);
        if (objDiv) {
            objDiv.scrollTop = objDiv.scrollHeight;
        }
    }
    showMessage(type,message){

        var ele = window.$(".global-message");
        ele.removeClass('bg-success');
        ele.removeClass('bg-danger');
        if(type == 'success'){
            ele.addClass('bg-success');
            ele.find('.error-heading').hide();
            ele.find('.success-heading').show();
        }else{
            ele.addClass('bg-danger');
            ele.find('.success-heading').hide();        
            ele.find('.error-heading').show();        
        }
        ele.find('.message-box').text(message);
        ele.show(0).delay(5000).hide(0);
        
    }

}