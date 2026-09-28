// ==========================================
// BACKEND API URLS
// ==========================================

const HALL_API = "/api/halls";
const STUDENT_API = "/api/students";

console.log("APP.JS LOADED");


// ==========================================
// PAGE LOAD
// ==========================================

document.addEventListener("DOMContentLoaded", function () {

    console.log("Frontend loaded successfully");

    loadHalls();
    loadStudents();

});


// ==========================================
// CREATE HALL
// ==========================================

document.getElementById("hallForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const hallName = document.getElementById("hallName").value.trim();
    const rows = Number(document.getElementById("hallRows").value);
    const columns = Number(document.getElementById("hallColumns").value);

    const message = document.getElementById("hallMessage");

    if (!hallName || rows <= 0 || columns <= 0) {

        message.className = "error";
        message.innerText = "Please enter valid hall details.";

        return;
    }

    const hallData = {
        hallName: hallName,
        rows: rows,
        columns: columns
    };

    console.log("Sending hall:", hallData);

    try {

        const response = await fetch(HALL_API, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(hallData)

        });

        const resultText = await response.text();

        console.log("Hall response:", response.status, resultText);

        if (!response.ok) {

            throw new Error(
                "Server Error " +
                response.status +
                ": " +
                resultText
            );

        }

        const savedHall = JSON.parse(resultText);

        message.className = "success";

        message.innerText =
            "Hall created successfully! ID: " +
            savedHall.id;

        document.getElementById("hallForm").reset();

        loadHalls();

    } catch (error) {

        console.error("CREATE HALL ERROR:", error);

        message.className = "error";

        message.innerText =
            "Hall creation failed: " +
            error.message;
    }

});


// ==========================================
// LOAD HALLS
// ==========================================

async function loadHalls() {

    const hallList = document.getElementById("hallList");

    hallList.innerHTML = "Loading halls...";

    try {

        const response = await fetch(HALL_API);

        console.log("GET HALL STATUS:", response.status);

        if (!response.ok) {

            throw new Error(
                "Unable to load halls. Status: " +
                response.status
            );

        }

        const halls = await response.json();

        console.log("HALL DATA:", halls);

        if (!Array.isArray(halls) || halls.length === 0) {

            hallList.innerHTML =
                '<div class="empty">No halls available.</div>';

            return;
        }

        hallList.innerHTML = "";

        halls.forEach(function (hall) {

            const div = document.createElement("div");

            div.className = "hall-card";

            div.innerHTML = `
                <h3>${escapeHTML(hall.hallName)}</h3>

                <p><strong>ID:</strong> ${hall.id}</p>

                <p><strong>Rows:</strong> ${hall.rows}</p>

                <p><strong>Columns:</strong> ${hall.columns}</p>

                <p><strong>Capacity:</strong> ${hall.capacity}</p>
            `;

            hallList.appendChild(div);

        });

    } catch (error) {

        console.error("LOAD HALL ERROR:", error);

        hallList.innerHTML = `
            <div class="error">
                Cannot connect to backend.<br>
                ${escapeHTML(error.message)}
            </div>
        `;
    }
}


// ==========================================
// CREATE STUDENT
// ==========================================

document.getElementById("studentForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const registerNumber =
        document.getElementById("registerNumber").value.trim();

    const studentName =
        document.getElementById("studentName").value.trim();

    const section =
        document.getElementById("studentSection").value.trim();

    const message =
        document.getElementById("studentMessage");


    if (!registerNumber || !studentName || !section) {

        message.className = "error";
        message.innerText =
            "Please enter all student details.";

        return;
    }


    const studentData = {

        registerNumber: registerNumber,

        studentName: studentName,

        section: section

    };


    console.log("Sending student:", studentData);


    try {

        const response = await fetch(STUDENT_API, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(studentData)

        });


        const resultText = await response.text();


        console.log(
            "Student response:",
            response.status,
            resultText
        );


        if (!response.ok) {

            throw new Error(
                "Server Error " +
                response.status +
                ": " +
                resultText
            );

        }


        message.className = "success";

        message.innerText =
            "Student registered successfully!";


        document.getElementById("studentForm").reset();


        loadStudents();


    } catch (error) {

        console.error(
            "CREATE STUDENT ERROR:",
            error
        );


        message.className = "error";

        message.innerText =
            "Student registration failed: " +
            error.message;

    }

});


// ==========================================
// LOAD STUDENTS
// ==========================================

async function loadStudents() {

    const studentList =
        document.getElementById("studentList");


    studentList.innerHTML =
        "Loading students...";


    try {

        const response =
            await fetch(STUDENT_API);


        console.log(
            "GET STUDENT STATUS:",
            response.status
        );


        if (!response.ok) {

            throw new Error(
                "Unable to load students. Status: " +
                response.status
            );

        }


        const students =
            await response.json();


        console.log(
            "STUDENT DATA:",
            students
        );


        if (
            !Array.isArray(students) ||
            students.length === 0
        ) {

            studentList.innerHTML =
                '<div class="empty">No students registered.</div>';

            return;
        }


        studentList.innerHTML = "";


        students.forEach(function (student) {

            const div =
                document.createElement("div");


            div.className =
                "student-card";


            div.innerHTML = `

                <h3>
                    ${escapeHTML(student.studentName)}
                </h3>

                <p>
                    <strong>ID:</strong>
                    ${student.id}
                </p>

                <p>
                    <strong>Register Number:</strong>
                    ${escapeHTML(student.registerNumber)}
                </p>

                <p>
                    <strong>Section:</strong>
                    ${escapeHTML(student.section)}
                </p>

            `;


            studentList.appendChild(div);

        });


    } catch (error) {

        console.error(
            "LOAD STUDENT ERROR:",
            error
        );


        studentList.innerHTML = `

            <div class="error">

                Cannot connect to student API.<br>

                ${escapeHTML(error.message)}

            </div>

        `;

    }

}


// ==========================================
// SECURITY HELPER
// ==========================================

function escapeHTML(value) {

    if (value === null || value === undefined) {

        return "";

    }

    return String(value)

        .replace(/&/g, "&amp;")

        .replace(/</g, "&lt;")

        .replace(/>/g, "&gt;")

        .replace(/"/g, "&quot;")

        .replace(/'/g, "&#039;");

}