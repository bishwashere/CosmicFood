module.exports = class Helper {

    userRole = {
        user:'CUSTOMER',
        driver:'DRIVER',
        restaurant:'RESTAURANT'
    }
    addToCart(id,details,message){
        if(typeof(details) == 'object'){
            details.quantity = parseInt(details.quantity);
            if(this.getCart().length <= 0){
                var items = [];
                items.push({id:id,details:details});
                localStorage.setItem('cartInfo', JSON.stringify(items));
                this.changeToHeader();

            }else{

                var oldItems = this.getCart();
                var count = 0;
                var found = false;
                oldItems.map((oldItem)=>{
                    if(oldItem.id == id){
                        oldItems[count].details = details;
                        found = true;
                        localStorage.setItem('cartInfo', JSON.stringify(oldItems));
                        this.changeToHeader();
                    
                    }
                    count++;
                })
                if(found === false){
                    oldItems.push({id:id,details:details});
                    localStorage.setItem('cartInfo', JSON.stringify(oldItems));
                    this.changeToHeader();
                }
                
            }
            if(message === false){
                 console.log('updated but message has not been shown.');
            }else{
                this.showMessage('success','Added Successfully.');
            }
            
        }        

    }
    getCart(){

            var localCart = JSON.parse(localStorage.getItem('cartInfo'));

            if (typeof (localCart) === 'object') {
                if(localCart == null){
                    return [];
                }else{
                    return localCart;
                }                
            }else{

                return [];
            }
            
    }
    makeCartEmpty(){

        localStorage.removeItem('cartInfo');
        this.changeToHeader();
    }
    changeToHeader(){
        if(document.querySelector(".cart-count")){
            document.querySelector(".cart-count").innerHTML = this.getCart().length;
        }
    }
    
    
    getUserInfo () {
            var userInfo = {
                id: '',
                username: '',
                userToken: '',
                firstName: '',
                lastName: '',
                email: '',
                role: '',
                profilePic: '',
            };
            var userInfoLocal = JSON.parse(localStorage.getItem('userInfo'));
            if (typeof (userInfoLocal) === 'object') {
                if (userInfoLocal !== null) {
                    if (typeof (userInfo.id) !== 'undefined') {
                        if (userInfoLocal.id !== '') {
                            userInfo.id = userInfoLocal.id;
                        }
                    }
                    if (typeof (userInfo.username) !== 'undefined') {
                        if (userInfoLocal.username !== '') {
                            userInfo.username = userInfoLocal.username;
                        }
                    }
                    if (typeof (userInfo.userToken) !== 'undefined') {
                        if (userInfoLocal.userToken !== '') {
                            userInfo.userToken = userInfoLocal.userToken;
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
            userInfoLocal.userToken = newToken;
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
                    alert('Api url is missing');
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

                            var ajaxParms = {};
                            ajaxParms['dataType'] = 'json';

                            if(typeof(params.auth) == 'undefined'){
                                ajaxParms['headers'] = {
                                    'Authorization':'Bearer '+this.getUserInfo().userToken
                                };
                            }else{
                                if(params.auth == true){
                                    ajaxParms['headers'] = {
                                        'Authorization':'Bearer '+this.getUserInfo().userToken
                                    };

                                }
                                
                            }
                            console.log(ajaxParms);
                            
                            ajaxParms['url'] = params.url;
                            ajaxParms['type'] = params.type;
                            ajaxParms['xhr'] = function () {
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
                                                };
                            ajaxParms['complete'] = (resp) =>{
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
                                    
                                };
                            ajaxParms['success'] = (resp) =>{
                                    if (typeof(params.success) == 'function') {
                                        params.success(resp);
                                    }
                            };
                            if(typeof(params.withData) != 'undefined'){
                                if(params.withData == 'json'){

                                    var object = {};
                                    var cnt = 0;
                                    if(typeof(params.withNotFormData) == 'undefined'){
                                            formData.forEach(function(value, key){
                                                object[key] = value;
                                                cnt++;
                                            });
                                            if(cnt > 0){
                                                ajaxParms['data'] = JSON.stringify(object);
                                                
                                            }
                                    }else{
                                        
                                            ajaxParms['data'] = JSON.stringify(params.data);
                                    }
                                    
                                    
                                    ajaxParms['contentType'] = 'application/json';
                                    
                                    window.$.ajax(ajaxParms);
                                }else{

                                    ajaxParms['data'] = params.data;
                                    ajaxParms['cache'] = false;
                                    ajaxParms['contentType'] = false;
                                    ajaxParms['processData'] = false;
                                    window.$.ajax(ajaxParms);
                                }
                            }else{
                                ajaxParms['data'] = params.data;
                                ajaxParms['cache'] = false;
                                ajaxParms['contentType'] = false;
                                ajaxParms['processData'] = false;
                                window.$.ajax(ajaxParms);
                            }

                            
                        } else {
                            console.log('jquery not loaded');
                        }

                    }//end      

                }
            }
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
        ele.stop();
        ele.css({
            opacity:1
        })
        ele.find('.message-box').text(message);
        ele.show(0).fadeOut(5000);

        
    }

}




