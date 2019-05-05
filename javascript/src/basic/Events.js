window.addEventListener('load', () => {
    console.log("Window loaded");
    document.getElementById("header").setAttribute("draggable",true);
})

window.addEventListener('scroll', (event) => {
    console.log("scrolling" + window.scrollY);
})


document.getElementById("container").addEventListener("click",(event)=>{
    event.stopPropagation();
    console.log(`container clicked. this even is ${event.bubbles? 'an' : 'not an'} event`);
},true) // this true will indicate "event capturing"

document.getElementById("middle").addEventListener("click",(event)=>{
    
    console.log(`middle clicked. this event is ${event.bubbles? 'a' : 'not a'} event`);
},true)

document.getElementById("header").addEventListener("drag",(event)=>{
   // event.stopPropagation();
    console.log(`header dragged. this event is ${event.bubbles? 'a' : 'not a'} event`);
})
document.getElementById("header").addEventListener("dragstart",(event)=>{
   // event.stopPropagation();
    console.log(`header dragstart event fired. this even is ${event.bubbles? 'a' : 'not a'} event`);
})
document.getElementById("header").addEventListener("dragend",(event)=>{
    // event.stopPropagation();
    console.log(`header dragOver event fired. this even is ${event.bubbles? 'a' : 'not a'} event`);
})