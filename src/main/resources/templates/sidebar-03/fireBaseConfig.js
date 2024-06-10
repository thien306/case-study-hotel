
// fireBaseConfig.js
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
import { getStorage, ref, uploadBytesResumable } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-storage.js";

const firebaseConfig = {
    apiKey: "AIzaSyDJFWT4Yza1VvGrr-30B6lgG1myNhmgHMY",
    authDomain: "case-study-hotel.firebaseapp.com",
    projectId: "case-study-hotel",
    storageBucket: "case-study-hotel.appspot.com",
    messagingSenderId: "770405144957",
    appId: "1:770405144957:web:583bce090fdf3093c31414",
    measurementId: "G-ZYTME4EJ38"
};

const app = initializeApp(firebaseConfig);
const storage = getStorage(app);

export { app, storage };
