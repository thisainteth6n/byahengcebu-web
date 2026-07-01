import {useState} from "react";
import axios from "axios";


function Register(){


const [user,setUser]=useState({
fullname:"",
email:"",
password:"",
role:"DRIVER"
})


const register=async()=>{

const res =
await axios.post(
"http://localhost:8080/api/auth/register",
user
)

alert(res.data)

}



return (

<div>

<h1>Register</h1>


<input
placeholder="Full Name"
onChange={
e=>setUser({...user,fullname:e.target.value})
}
/>


<br/>


<input
placeholder="Email"
onChange={
e=>setUser({...user,email:e.target.value})
}
/>


<br/>


<input
placeholder="Password"
type="password"
onChange={
e=>setUser({...user,password:e.target.value})
}
/>


<br/>


<select
onChange={
e=>setUser({...user,role:e.target.value})
}
>

<option>DRIVER</option>
<option>ADMIN</option>

</select>


<br/>


<button onClick={register}>
Register
</button>


</div>

)


}


export default Register;