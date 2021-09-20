import axios from 'axios'

const url = "http://localhost:8080";

export default {
    login(params:object){
        return axios.get(url+"/api/login/loginaction",{params:params})
            .then(response=>{
                let data= response.data;
                return data.data
            })
            .catch(error =>{
                console.log(error);
            })
    }
}
