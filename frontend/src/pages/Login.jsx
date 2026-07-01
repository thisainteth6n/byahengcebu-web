import {useState} from "react";
import axios from "axios";


function Login(){


const [user,setUser]=useState({
email:"",
password:""
})


const login=async()=>{


const res =
await axios.post(
"http://localhost:8080/api/auth/login",
user
)


alert(res.data)


}



return (

<div>

<h1>Login</h1>


<input
placeholder="Email"
onChange={
e=>setUser({...user,email:e.target.value})
}
/>


<br/>


<input
type="password"
placeholder="Password"
onChange={
e=>setUser({...user,password:e.target.value})
}
/>


<br/>


<button onClick={login}>
Login
</button>


</div>

)


}


export default Login;