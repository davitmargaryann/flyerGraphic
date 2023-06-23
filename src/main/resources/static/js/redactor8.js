const canvas = new fabric.Canvas("canvas");
let background = document.getElementById("background-image");

background.crossOrigin = '*';
background.onload = function () {
    var ctx = canvas.getContext('2d');
    ctx.drawImage(background, 0, 0, 1080, 1080);
    let fabricStyle8Background = new fabric.Image(background);
    fabricStyle8Background.crossOrigin = '*';
    fabricStyle8Background.set({
        top: 0,
        left: 0,
    })
    fabricStyle8Background.selectable = false;
    canvas.add(fabricStyle8Background);
    fabricStyle8Background.sendToBack();
    canvas.renderAll();
}


let testText = new fabric.Text('Test Text');
testText.set({
    left: 60,
    fontSize: 60,
    fill: 'black'
})

canvas.add(testText)
testText.centerH();
testText.bringToFront();
canvas.renderAll();

