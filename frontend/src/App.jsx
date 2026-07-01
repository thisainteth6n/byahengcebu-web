import { useState } from "react";
import "./App.css";

function App() {

  const [registerData, setRegisterData] = useState({
    fullname: "",
    email: "",
    password: "",
    role: "DRIVER"
  });

  const [loginData, setLoginData] = useState({
    email: "",
    password: ""
  });


  const register = async (e) => {
    e.preventDefault();

    const response = await fetch(
      "http://localhost:8080/api/auth/register",
      {
        method:"POST",
        headers:{
          "Content-Type":"application/json"
        },
        body:JSON.stringify(registerData)
      }
    );

    if(response.ok){
      alert("Registration successful!");
    }
    else{
      alert("Registration failed");
    }
  };


  const login = async(e)=>{
    e.preventDefault();

    const response = await fetch(
      "http://localhost:8080/api/auth/login",
      {
        method:"POST",
        headers:{
          "Content-Type":"application/json"
        },
        body:JSON.stringify(loginData)
      }
    );


    if(response.ok){
      alert("Login successful!");
    }
    else{
      alert("Invalid credentials");
    }

  }



return (

<div className="container">


<div className="card">

<h1>ByahengCebu</h1>
<p className="subtitle">
Fleet Management System
</p>


<h2>Create Account</h2>


<form onSubmit={register}>


<label>Full Name</label>

<input
placeholder="Enter full name"
onChange={(e)=>
setRegisterData({
...registerData,
fullname:e.target.value
})
}
/>


<label>Email</label>

<input
type="email"
placeholder="Enter email"
onChange={(e)=>
setRegisterData({
...registerData,
email:e.target.value
})
}
/>


<label>Password</label>

<input
type="password"
placeholder="Enter password"
onChange={(e)=>
setRegisterData({
...registerData,
password:e.target.value
})
}
/>


<label>Role</label>

<select
onChange={(e)=>
setRegisterData({
...registerData,
role:e.target.value
})
}
>

<option>DRIVER</option>
<option>ADMIN</option>

</select>


<button>
Register
</button>


</form>

</div>





<div className="card">


<h2>Login</h2>


<form onSubmit={login}>


<label>Email</label>

<input
type="email"
placeholder="Enter email"
onChange={(e)=>
setLoginData({
...loginData,
email:e.target.value
})
}
/>


<label>Password</label>

<input
type="password"
placeholder="Enter password"
onChange={(e)=>
setLoginData({
...loginData,
password:e.target.value
})
}
/>


<button>
Login
</button>


</form>


</div>


</div>


)

}


export default App;