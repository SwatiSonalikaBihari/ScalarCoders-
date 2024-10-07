const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');
const password=document.getElementById('password');
const eye=document.getElementById('eye');
registerBtn.addEventListener('click', () => {
    container.classList.add("active");
});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
});

eye.addEventListener("click",function(){
	const inputtype=password.getAttribute('type') === 'password' ? 'text':'password' ;
	password.setAttribute('type',inputtype);
	
	this.classList.toggle('fa-eye') ;
	this.classList.toggle('fa-eye-slash') ;
},false)

