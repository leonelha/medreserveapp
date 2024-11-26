/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//async function fetchData(){
function fetchData() {
    try {
        //const response = await fetch('DataServlet');
        //const data = await response.json();

        // Puedes comentar esta línea si no necesitas datos simulados.
        const data = [
            { id: 1, client: "John Doe", specialty: "Cardiology", hour: "10:00 AM" },
            { id: 2, client: "Jane Smith", specialty: "Neurology", hour: "11:30 AM" },
            { id: 3, client: "Michael Brown", specialty: "Orthopedics", hour: "01:00 PM" },
            { id: 4, client: "Emily Davis", specialty: "Pediatrics", hour: "02:30 PM" },
            { id: 5, client: "Sarah Wilson", specialty: "Dermatology", hour: "03:00 PM" },
            { id: 6, client: "James Taylor", specialty: "Gastroenterology", hour: "09:00 AM" },
            { id: 7, client: "Linda Johnson", specialty: "Endocrinology", hour: "04:00 PM" },
            { id: 8, client: "Robert Martinez", specialty: "Pulmonology", hour: "05:30 PM" },
            { id: 9, client: "Patricia Garcia", specialty: "Rheumatology", hour: "08:00 AM" },
            { id: 10, client: "William Anderson", specialty: "Hematology", hour: "10:30 AM" },
            { id: 11, client: "Jessica Lee", specialty: "Infectious Diseases", hour: "11:00 AM" },
            { id: 12, client: "Charles Robinson", specialty: "Nephrology", hour: "01:30 PM" },
            { id: 13, client: "Amanda Wilson", specialty: "Urology", hour: "03:30 PM" },
            { id: 14, client: "Daniel Harris", specialty: "Ophthalmology", hour: "02:00 PM" },
            { id: 15, client: "Nancy Walker", specialty: "Plastic Surgery", hour: "12:00 PM" },
            { id: 16, client: "Matthew Young", specialty: "Gynecology", hour: "04:30 PM" },
            { id: 17, client: "Deborah King", specialty: "Orthopedic Surgery", hour: "05:00 PM" },
            { id: 18, client: "Christopher Wright", specialty: "Oncology", hour: "09:30 AM" },
            { id: 19, client: "Laura Scott", specialty: "Pediatric Surgery", hour: "10:00 AM" },
            { id: 20, client: "Joseph Green", specialty: "Thoracic Surgery", hour: "01:00 PM" }
        ];


        const rowsPerPage = 15;
        let currentPage = 1;

        function renderTable(data, page) {
            const tableBody = document.querySelector('#dataTable tbody');
            tableBody.innerHTML = '';

            const start = (page - 1) * rowsPerPage;
            const end = start + rowsPerPage;
            const paginatedData = data.slice(start, end);

            paginatedData.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.id}</td>
                    <td>${item.client}</td>
                    <td>${item.specialty}</td>
                    <td>${item.hour}</td>
                `;
                tableBody.appendChild(row);
            });
        }

        function renderPagination(data) {
            const pagination = document.getElementById('pagination');
            pagination.innerHTML = '';

            const totalPages = Math.ceil(data.length / rowsPerPage);

            if (currentPage > 1) {
                const prevButton = document.createElement('button');
                prevButton.textContent = 'Previous';
                prevButton.addEventListener('click', () => {
                    currentPage--;
                    renderTable(data, currentPage);
                    renderPagination(data);
                });
                pagination.appendChild(prevButton);
            } else {
                const prevButton = document.createElement('button');
                prevButton.textContent = 'Previous';
                prevButton.className = 'disabled';
                pagination.appendChild(prevButton);
            }

            for (let i = 1; i <= totalPages; i++) {
                const pageButton = document.createElement('button');
                pageButton.textContent = i;
                if (i === currentPage) {
                    pageButton.classList.add('disabled');
                } else {
                    pageButton.addEventListener('click', () => {
                        currentPage = i;
                        renderTable(data, currentPage);
                        renderPagination(data);
                    });
                }
                pagination.appendChild(pageButton);
            }

            if (currentPage < totalPages) {
                const nextButton = document.createElement('button');
                nextButton.textContent = 'Next';
                nextButton.addEventListener('click', () => {
                    currentPage++;
                    renderTable(data, currentPage);
                    renderPagination(data);
                });
                pagination.appendChild(nextButton);
            } else {
                const nextButton = document.createElement('button');
                nextButton.textContent = 'Next';
                nextButton.className = 'disabled';
                pagination.appendChild(nextButton);
            }
        }

        renderTable(data, currentPage);
        renderPagination(data);
        
    } catch (error) {
        console.log('Error fetching data:', error);
    }
}

window.onload = fetchData;
