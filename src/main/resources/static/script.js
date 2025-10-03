const todoAdd = () => {
  return `
  <form action="" id="add-todo-form">
      <h1>New Todo</h1>
      <div class="form-component">
        <p>Title</p>
        <input
          class="input"
          type="text"
          name="title"
          id="titleInput"
          required
        />
      </div>

      <div class="form-component">
        <p>Discription</p>
        <textarea
          class="input"
          name="discription"
          id="discriptionInput"
          required
        ></textarea>
      </div>

      <button class="todo-add-btn" type="submit">Add todo</button>
    </form>
  `;
};
const confirmDelete = (element) => {
  const data = element.split("$");
  return `
  <div class="delModal">
      <p class="title">Delete Todo</p>
      <form id="todo-del-form">
      <input class="hidden" name="todoId" value="${data[0]}" type="text">
      <div class="">
        <p class="todo-title">${data[1]}</p>
        <p class="todo-discription">${data[2]}</p>
      </div>
      <button class="confirm-btn" type="submit">Confirm deleting</button>
      <form>
    </div>
  `;
};

const updateTodo = (element) => {
  const data = element.split("$");
  return `
  <form action="" id="todo-up-form">
          <h1>Update Todo</h1>
          <input class="hidden" name="todoId" value="${data[0]}" type="text">
          <div class="form-component">
            <p>Title</p>
            <input
              class="input"
              type="text"
              name="title"
              id="titleInput"
              value="${data[1]}"
              required
            />
          </div>

          <div class="form-component">
            <p>Discription</p>
            <textarea
              class="input"
              name="discription"
              value=""
              required
            >${data[2]}</textarea>
          </div>
          <button class="todo-add-btn" type="submit">Add todo</button>
        </form>
  `;
};
const todoaddicon = document.getElementById("openModal");
const closeModal = document.getElementById("closeModal");
const todolist = document.getElementById("todolist");
const modal = document.querySelector(".modal");

todoaddicon.addEventListener("click", function () {
  modal.classList.remove("hidden");
  document.getElementById("mc").innerHTML = todoAdd();
  document
    .getElementById("add-todo-form")
    .addEventListener("submit", async function (e) {
      e.preventDefault();
      const data = new FormData(e.target);

      const responce = await fetch("http://localhost:4000/todos", {
        method: "POST",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify({
          title: data.get("title"),
          discription: data.get("discription"),
        }),
      });
      if (responce.ok) modal.classList.add("hidden");
      loadData();
    });
});
closeModal.addEventListener("click", function () {
  modal.classList.add("hidden");
  document
    .getElementById("add-todo-form" || "todo-up-form" || "todo-del-form")
    .removeEventListener("submit", function () {});
  document.getElementById("mc").innerHTML = "";
});

const loadData = async function () {
  const responce = await fetch("http://localhost:4000/todos");
  if (!responce.ok)
    return todolist.insertAdjacentHTML(
      "beforeend",
      `
      <p class="not-found">Todo Not Found!</p>
        `
    );
  const todos = await responce.json();
  todolist.innerHTML = "";
  todos.forEach((element) => {
    todolist.insertAdjacentHTML(
      "beforeend",
      `
      <div class="todo-item">
        <div class="item-header">
          <p class="todo-title">${element.title}</p>
          <div>
          <input class="hidden" value="${
            element.id + "$" + element.title + "$" + element.discription
          }" type="text">
          <button class="btn-blue up-btn" >Edit</button>
          <button class="btn-red dl-btn">delete</button>
          </div>
        </div>
        <p class="todo-discription">${element.discription}</p>
      </div>
        `
    );
  });

  if (todos != null) {
    document.querySelectorAll(".up-btn").forEach((Item) =>
      Item.addEventListener("click", function (e) {
        modal.classList.remove("hidden");
        document.getElementById("mc").innerHTML = updateTodo(
          e.target.parentNode.children[0].value
        );
        document
          .getElementById("todo-up-form")
          .addEventListener("submit", async function (e) {
            e.preventDefault();
            const data = new FormData(e.target);

            const resualt = await fetch(
              `http://localhost:4000/todos/${data.get("todoId")}`,
              {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                  title: data.get("title"),
                  discription: data.get("discription"),
                }),
              }
            );
            if (responce.ok) modal.classList.add("hidden");
            loadData();
          });
      })
    );
    document.querySelectorAll(".dl-btn").forEach((Item) =>
      Item.addEventListener("click", function (e) {
        modal.classList.remove("hidden");
        document.getElementById("mc").innerHTML = confirmDelete(
          e.target.parentNode.children[0].value
        );
        document
          .getElementById("todo-del-form")
          .addEventListener("submit", async function (e) {
            e.preventDefault();
            const data = new FormData(e.target);

            const resualt = await fetch(
              `http://localhost:4000/todos/${data.get("todoId")}`,
              {
                method: "DELETE",
              }
            );
            if (responce.ok) modal.classList.add("hidden");
            loadData();
          });
      })
    );
  }
};

window.onload = loadData();
