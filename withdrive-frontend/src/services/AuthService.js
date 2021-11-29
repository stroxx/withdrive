import axios from 'axios';
import authHeader from './AuthHeader';
    const API_URL = "http://localhost:8080/user/";

    class AuthService {
    login(username, password) {
        var reqBody = "username="+username+"&password="+password+"&grant_type=password";
        return fetch(API_URL + "login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: reqBody
        })
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(email,firstName,lastName,dateOfBirth,gender,phoneNumber,password){
        return axios.post(API_URL + "register", {
            email,
            firstName,
            lastName,
            dateOfBirth,
            gender,
            phoneNumber,
            password
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }

    checkToken(){
        const check = () => {
            return axios.get(API_URL + "check",{headers: authHeader()}).then(response => {return true}).catch(error => {return false;});
        }
        return check();
    }

    checkTokenAdmin(){
        const check = () => {
            return axios.get(API_URL + "check/admin",{headers: authHeader()}).then(response => {return true}).catch(error => {return false;});
        }
        return check();
    }
}

    export default new AuthService();