const expand_btn = document.querySelector(".expand-btn");
const img = expand_btn.querySelector("img");

let activeIndex;

expand_btn.addEventListener("click", () => {
  document.body.classList.toggle("collapsed");
});

const current = window.location.href;

const allLinks = document.querySelectorAll(".sidebar-links a");

allLinks.forEach((elem) => {
  elem.addEventListener('click', function() {
    const hrefLinkClick = elem.href;

    allLinks.forEach((link) => {
      if (link.href == hrefLinkClick){
        link.classList.add("active");
      } else {
        link.classList.remove('active');
      }
    });
  })
});

function handleUploadImgFireBase(e){
  console.log("a",e)
  // let image = $("#create-image")[0].files[0];
  // let storageRef =app.storage().ref("/image",image.name);
  // let upload = storageRef.put(image);
  // upload.on("state_changed",(snapshot)=>{
  //     console.log("snapshot",snapshot)
  // },(error)=>{
  //     console.log("error",error)
  // })
}
