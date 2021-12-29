const addButton = document.getElementById('addButton');
const questionsBlock = document.getElementById('questions');
let index = 0;
addButton.addEventListener("click", () => {
    const valuesMap = new Map();
    for(let item of questionsBlock.children) {
        if(item.tagName === 'BR')
            continue;
        valuesMap.set(item.attributes.getNamedItem('name').value, item.value);
    }
    questionsBlock.innerHTML += `<input type='text' name='question-${index++}' placeholder='Введите вопрос' required><br/>`;
    valuesMap.forEach((value,name)=>{
        questionsBlock.children.namedItem(name).value = value;
    })
})