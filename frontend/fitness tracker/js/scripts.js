document.addEventListener("DOMContentLoaded", function () {
    const planWorkoutBtn = document.getElementById("planWorkoutBtn");

    planWorkoutBtn.addEventListener("click", function () {
        const startTime = document.getElementById("startTime").value;
        const workoutLength = document.getElementById("workoutLength").value;
        const bodyType = document.getElementById("bodyType").value;

        // Example: Create a workout plan based on body type
        let workoutPlan = "";
        switch (bodyType) {
            case "endurance":
                workoutPlan = "30 minutes of cardio (running/cycling), 15 minutes of flexibility exercises";
                break;
            case "strength":
                workoutPlan = "45 minutes of weightlifting, focusing on compound exercises";
                break;
            case "flexibility":
                workoutPlan = "20 minutes of yoga, 20 minutes of stretching exercises";
                break;
            default:
                workoutPlan = "No specific workout plan available";
        }

        // Display the workout plan
        alert(`Workout Plan:\nStart Time: ${startTime}\nWorkout Length: ${workoutLength} minutes\nBody Type: ${bodyType}\n\n${workoutPlan}`);
    });

    const submitFeedbackBtn = document.getElementById("submitFeedbackBtn");

    submitFeedbackBtn.addEventListener("click", function () {
    const feedbackText = document.getElementById("feedbackText").value;

        // Save or process the feedback (for now, we'll just log it to the console)
        console.log(`User Feedback: ${feedbackText}`);

        // Provide a confirmation to the user (you can customize this as needed)
        alert("Thank you for your feedback!");

        
        
        
        
        document.addEventListener("DOMContentLoaded", function () {
            // Adaugă eveniment de click pe butonul "Plan Workout"
            document.getElementById("planWorkoutBtn").addEventListener("click", function () {
                planWorkout();
            });
        
            function planWorkout() {
                // Obține valorile introduse de utilizator
                var workoutLength = document.getElementById("workoutLength").value;
                var trainingCategory = document.getElementById("trainingCategory").value;
                var exerciseIntensity = document.getElementById("exerciseIntensity").value;
                var muscleClass = document.getElementById("muscleClass").value;
        
                // Creează un obiect cu datele
                var workoutData = {
                    "workoutLength": workoutLength,
                    "trainingCategory": trainingCategory,
                    "exerciseIntensity": exerciseIntensity,
                    "muscleClass": muscleClass
                };
        
                // Trimite datele către backend folosind fetch API
                fetch('http://localhost:8080/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(workoutData)
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Răspuns de la server:', data);
                })
                .catch(error => {
                    console.error('Eroare:', error);
                });
            }
        });
});
});
