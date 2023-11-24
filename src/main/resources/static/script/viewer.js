
cornerstoneTools.init();
var toolbarBtn = document.getElementById("Toolbar_btn");
const thumbnailBtn = document.getElementById("thumbnail_btn");
let isThumbnailVisible = true;
let isToolbarVisible = true;

/** Thumbnail **/
thumbnailBtn.addEventListener("click", () => {
    const thumbnailContainer = document.getElementById("thumbnail-container");
    const studyKey = document.getElementById("studyId").value;

    if (isThumbnailVisible) {
        thumbnailContainer.style.display = "block";
        showThumbnail(studyKey);
    } else {
        thumbnailContainer.style.display = "none";
        var tbody = document.querySelector("#thumbnail-container tbody");
        tbody.innerHTML = "";
    }
    isThumbnailVisible = !isThumbnailVisible;
});

toolbarBtn.addEventListener("click", () => {
    var toolbar = document.getElementById("toolbar");

    if (isToolbarVisible) {
        toolbar.style.display = "none";
    } else {
        toolbar.style.display = "block";
    }
    isToolbarVisible = !isToolbarVisible;
});

function showThumbnail(path) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/studies/getThumbnail/" + path, true);
    xhr.setRequestHeader("Content-Type", "application/json")

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var imagesData = JSON.parse(xhr.responseText);
                displayImages(imagesData);
            } else {
                alert("Failed - Status code: " + xhr.status);
            }
        }
    };
    xhr.send();
}

function displayImages(images) {
    var tbody = document.querySelector("#thumbnail-container tbody");

    var sortedImages = Object.values(images).sort(function (a, b) { // 이미지 배열을 serieskey에 대해 오름차순으로 정렬
        return a.serieskey - b.serieskey;
    });

    for (var i = 0; i < sortedImages.length; i++) {
        var base64Image = sortedImages[i].image;
        var seriesKey = sortedImages[i].serieskey;
        var seriesDesc = sortedImages[i].seriesdesc;

        var tr = document.createElement("tr");
        var td = document.createElement("td");
        var divImg = document.createElement("div");
        var img = document.createElement("img");
        var divDesc = document.createElement("div");

        divDesc.className = "thumbnail-desc";
        divDesc.innerHTML = `Series Number : ${seriesKey}<br> &nbsp&nbsp&nbsp Series Desc : ${seriesDesc}`;
        img.src = "data:image/jpeg;base64," + base64Image;

        divImg.appendChild(img);
        td.appendChild(divDesc);
        td.appendChild(divImg);
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
}

/** To Worklist **/
document.getElementById("list_btn").addEventListener("click", function() {
    window.location.href = "/list";
})

// function activateReset(element){
//
//     cornerstone.enable(element);
//     console.log("element:"+element);
//     document.getElementById("reset").addEventListener('click', function (e) {
//         cornerstone.reset(element);
//     });
// }




function activateReset(id) {
    const element = document.getElementById(id);
    cornerstone.enable(element);
    console.log("element:", element);
    document.getElementById('reset').addEventListener('click', function (e) {
        console.log("리셋");
        cornerstone.reset(element);
        const toolStateManager = cornerstoneTools.globalImageIdSpecificToolStateManager; // Reset Annotations
        toolStateManager.clear(element);
        cornerstone.updateImage(element);
    });

}





