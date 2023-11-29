<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/style/listgrid.css">
    <link rel="icon" href="/images/favicon_logo.png" sizes="16x16" type="image/png">
    <title>DicomBridge</title>
</head>
<body>
<div id="container">
    <header>
        <img src="/images/dicombridge_logo.png" alt="logo" id="title_btn" style="width: 350px;">
    </header>

    <section id="bar-container">
        <nav>
            <ul>
                <li><img src="/images/user.png" alt="Adminstrato">
                    <div>Administrator</div>
                </li>
                <li class="nav_li" id="Dsearch">
                    <div class="image-container" id="Dsearch_div">
                        <img class="normal-image" src="/images/search.png" alt="thumbnail">
                        <img class="hover-image" src="/images/search_click.png" alt="thumbnail">
                    </div>
                    <div></div>
                </li>
                <li id="settings_btn">
                    <div class="image-container">
                        <img class="normal-image" src="/images/settings.png" alt="settings">
                        <img class="hover-image" src="/images/settings_click.png" alt="settings">
                    </div>
                </li>
                <li id="logout_btn">
                    <div class="image-container">
                        <img class="normal-image" src="/images/logout.png" alt="logout">
                        <img class="hover-image" src="/images/logout_click.png" alt="logout">
                    </div>
                </li>
            </ul>
        </nav>

        <aside id="Detailed-search" style="display: none;">
            <ul>
                <li>
                    <div id="calender"></div>
                </li>
                <li>
                    <span></span>
                </li>
                <li>
                    <span></span>
                </li>
                <li>
                    <span></span>
                </li>
                <li>
                </li>
            </ul>
        </aside>
    </section>
    <section id="list-contents">
        <aside id="study-search">
            <div id="search-bar">
                <a class="subtitle">검색</a>
            </div>
            <ul>
                <li><input type="text" class="keyword" id="Pid-input" placeholder="환자 아이디">
                    <input type="text" class="keyword" id="Pname-input" placeholder="환자 이름">
                    <select id="category" class="keyword">
                        <option value="">판독상태</option>
                        <option value="3">읽지않음</option>
                        <option value="4">열람중</option>
                        <option value="5">예비판독</option>
                        <option value="6">판독</option>
                    </select>
                    <button id="search" class="search">검색</button>
                </li>
                <li id="searchAll" class="search">전체</li>
                <li>1일</li>
                <li>3일</li>
                <li>1주일</li>
            </ul>
            <div id="search-result">
                <a class="subtitle">총 검사 건수 : </a>
                <span id="studyCount"></span>
                <button class="download-btn">다운로드</button>
            </div>
        </aside>
        <section id="mainContent">
            <div class="table-container">
                <input type="hidden" id="selectedImageIds" name="selectedImageIds" />
                <table id="mainTable">
                    <thead id="header-container">
                        <tr id="trTitle">
                            <th><input type="checkbox" id="masterCheckbox"></th>
                            <th>번호</th>
                            <th>환자 아이디</th>
                            <th>환자 이름</th>
                            <th>검사장비</th>
                            <th class="study">검사설명</th>
                            <th>검사일시</th>
                            <th>판독상태</th>
                            <th>시리즈</th>
                            <th>이미지</th>
                            <th>Verify</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </section>
        <footer>
            <div class="left-div">
                <div>
                    <li class="subtitle">Previous</li>
                </div>
                <div>
                    <div class="pid-div">
                        <li>환자 아이디 :</li>
                        <span id="span-pid"></span>
                    </div>
                    <div class="pname-div">
                        <li>환자 이름 :</li>
                        <span id="span-pname"></span>
                    </div>
                </div>
                <div class="table-div">
                    <table id="previousTable">
                        <tr>
                            <th>검사장비</th>
                            <th>검사설명</th>
                            <th>검사일시</th>
                            <th>판독상태</th>
                            <th>시리즈</th>
                            <th>이미지</th>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="right-div">
                <div class="subtitleAndButton"><a class="subtitle">Report</a>
                    <button class="checkButton">판독 지우기</button>
                </div>
                <div class="cell-a"><textarea placeholder="코멘트" readonly></textarea></div>
                <div class="cell-b">
                    <textarea name="interpretation" id="interpretation"></textarea>
                </div>
                <div class="cell-c">
                    <div>
                        <div>판독 매크로</div>
                        <div><select></select></div>
                    </div>
                    <div>
                        <div>Report Code</div>
                        <div id="reportStatusSelectContainer">
                            <select></select>
                        </div>
                    </div>
                    <div>
                        <div>예비판독의</div>
                        <div><input type="text" id="text5"></div>
                    </div>
                    <div>
                        <div>판독의1</div>
                        <div><input type="text" id="text3">></div>
                    </div>
                    <div>
                        <div>판독의2</div>
                        <div><input type="text"></div>
                    </div>
                    <div>
                        <button class="checkButton">판독</button>
                        <button class="checkButton">예비판독</button>
                    </div>
                </div>

            </div>
        </footer>
    </section>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/script/list.js"></script>
<script src="/script/report.js"></script>
</body>
</html>