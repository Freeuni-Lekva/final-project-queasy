

<style>
  body {
    background: linear-gradient(#5f2c82,#49a09d);
  }

  .center {
    /*background:url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' height='100%25' width='100%25'%3E%3Cdefs%3E%3Cpattern id='doodad' width='40' height='40' viewBox='0 0 40 40' patternUnits='userSpaceOnUse' patternTransform='rotate(135)'%3E%3Crect width='100%25' height='100%25' fill='%232a4365'/%3E%3Cpath d='M0 29a 9-9 0 0 0 9-9a 11-11 0 0 1 11-11v2a-9 9 0 0 0-9 9a-11 11 0 0 1-11 11zM0 69a 9-9 0 0 0 9-9a 11-11 0 0 1 11-11v2a-9 9 0 0 0-9 9a-11 11 0 0 1-11 11z' fill='%231a202c'/%3E%3Cpath d='M20 29.5a 9.5-9.5 0 0 0 9.5-9.5a 10.5-10.5 0 0 1 10.5-10.5v1a-9.5 9.5 0 0 0-9.5 9.5a-10.5 10.5 0 0 1-10.5 10.5z' fill='%23ecc94b'/%3E%3C/pattern%3E%3C/defs%3E%3Crect fill='url(%23doodad)' height='200%25' width='200%25'/%3E%3C/svg%3E ");*/
    background: #35394a;
    color: white;
    font-family: 'Source Code Pro', monospace;
    border: 10px solid #35394a;
    border-radius: 10px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    padding: 10px;
  }

  input[type=submit] {
    display: inline-block;
    outline: 0;
    border: none;
    box-shadow: none;
    cursor: pointer;
    padding: 9px 22px;
    font-size: 22px;
    height: 50px;
    font-weight: 400;
    color: #fff;
    text-align: center;
    line-height: normal;
    background: linear-gradient(90deg,#5522fa 0,#0074e4 100%);
    border-radius: 50px;
    transition: color .2s ease,background-color .2s ease,box-shadow .2s ease;
  }

  input[type=submit]:hover{
    box-shadow: 0 0 0 0.15rem #5ceace;
  }

  input[type=text], input[type=password] {
      font-family: 'Source Code Pro', monospace;

      width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
  }

  .center  a {
    color: white
  }
  .center a:hover {
    color: indianred;
  }
  a:checked {
    color: red
  }
</style>