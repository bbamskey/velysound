export const ex_category = [
    {
        id: "",
        name: "ALL",
    },
    {
        id: "hm",
        name: "HYMN",
    },
    {
        id: "cc",
        name: "CCM",
    },
    {
        id: "kp",
        name: "K-POP",
    },
    {
        id: "pp",
        name: "POP",
    },
];

export const ex_getCategoryName = (id) => {
    let r = "";
    ex_category.forEach((v) => {
        if (v.id === id) {
            r = v.name;
        }
    });
    return r;
};

export const ex_hero = [
    {
        id: 1,
        src: `${process.env.PUBLIC_URL}/assets/img/banner/forest.jpg`,
        text: `VELY SOUND`,
        description: `블라사운드에 방문해 주셔서<br/>정말 감사합니다 :)`,
    },
    {
        id: 2,
        src: `${process.env.PUBLIC_URL}/assets/img/banner/guitar.jpg`,
        text: `PLAY & FUN`,
        description: `당신의 삶을<br/>최고로 멋지게 연주하고<br/>최선을 다해 즐기세요 :)`,
    },
];

export const ex_video = [
    {
        id: 1,
        title: `공감하시네`,
        src: `https://www.youtube.com/embed/5x5d9X7zsEY`,
        imgSrc: `${process.env.PUBLIC_URL}/assets/img/thumb/thumb_001.jpg`,
        orgKey: "E",
        keyArr: ["C", "D", "E"],
    },
    {
        id: 2,
        title: `오직예수뿐이네`,
        src: `https://www.youtube.com/embed/P1RpJkNGeAw`,
        imgSrc: `${process.env.PUBLIC_URL}/assets/img/thumb/thumb_002.jpg`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
    },
    {
        id: 3,
        title: `주가일하시네`,
        src: `https://www.youtube.com/embed/asqp1qJFBJk`,
        imgSrc: `${process.env.PUBLIC_URL}/assets/img/thumb/thumb_003.jpg`,
        orgKey: "G",
        keyArr: ["C", "D", "E", "F", "G", "Ab", "A", "B"],
    },
    {
        id: 4,
        title: `전능하신나의주하나님은`,
        src: `https://www.youtube.com/embed/pdMnCbtOEcY`,
        imgSrc: `${process.env.PUBLIC_URL}/assets/img/thumb/thumb_004.jpg`,
        orgKey: "Bb",
        keyArr: ["Bb"],
    },
];

export const ex_sheet = [
    {
        id: 1,
        title: `[찬송가] 1 만복의 근원 하나님`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/001.jpeg`,
        price: `700,001`,
        orgKey: "A",
        keyArr: ["A", "B"],
        buyDate: "2022.05.05",
        limitDate: "2022.08.04",
    },
    {
        id: 2,
        title: `[찬송가] 2 찬양 성부 성자 성령`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/002.jpeg`,
        price: `700,002`,
        orgKey: "C#",
        keyArr: ["C", "C#", "E", "F", "Ab", "A", "B"],
        buyDate: "2022.01.05",
        limitDate: "2022.03.04",
    },
    {
        id: 3,
        title: `[찬송가] 3 성부 성자와 성령`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/003.jpeg`,
        price: `700,003`,
        orgKey: "E",
        keyArr: ["E"],
        buyDate: "2022.01.01",
        limitDate: "2022.03.31",
    },
    {
        id: 4,
        title: `[찬송가] 4 성부 성자와 성령`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/004.jpeg`,
        price: `700,004`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2022.08.20",
        limitDate: "2022.11.19",
    },
    {
        id: 5,
        title: `[찬송가] 5 이 천지간 만물들아`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/005.jpeg`,
        price: `700,005`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2022.07.07",
        limitDate: "2022.10.06",
    },
    {
        id: 6,
        title: `[찬송가] 6 목소리 높여서`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/006.jpeg`,
        price: `700,006`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2021.04.28",
        limitDate: "2021.06.11",
    },
    {
        id: 7,
        title: `[찬송가] 7 성부 성자 성령`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/007.jpeg`,
        price: `700,007`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2020.04.28",
        limitDate: "2020.06.11",
    },
    {
        id: 8,
        title: `[찬송가] 8 거룩 거룩 거룩 전능하신 주님`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/008.jpeg`,
        price: `700,008`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2010.01.28",
        limitDate: "2010.03.11",
    },
    {
        id: 9,
        title: `[찬송가] 9 하늘에 가득 찬 영광의 하나님`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/009.jpeg`,
        price: `700,009`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2022.01.28",
        limitDate: "2022.03.11",
    },
    {
        id: 10,
        title: `[찬송가] 10 전능왕 오셔서`,
        src: `${process.env.PUBLIC_URL}/assets/img/sheet/010.jpeg`,
        price: `700,010`,
        orgKey: "D",
        keyArr: ["C", "D", "E", "F", "Ab", "A", "B"],
        buyDate: "2022.06.30",
        limitDate: "2022.08.29",
    },
];

export const ex_sheet_detail = {
    id: 30,
    title: `[찬송가] 30 전능하고 놀라우신`,
    src: `${process.env.PUBLIC_URL}/assets/img/sheet/030.jpeg`,
    price: `1,999,700,030`,
    instrument: "Piano",
    orgKey: "D Major",
    page: 1,
    keyArr: ["C", "C#", "D", "D#", "E", "F", "G#", "Ab", "A", "B"],
    buyDate: "2022.06.30",
    limitDate: "2022.08.29",
    videoSrc: `https://www.youtube.com/embed/5x5d9X7zsEY`,
    tagArr: ["찬송가", "hymn", "30장", "찬송가편곡", "hymn arragement", "30th"],
    content:
        "<li>안녕하세요 블리사운드입니다 :)</li>" +
        "<li><strong>상품구매 후 100일동안 다운로드 받으실 수 있습니다.</strong></li>" +
        "<li>디지털 상품의 특성상 결제완료 후에는 취소처리가 불가합니다.</li>" +
        "<li>100일동안 자유롭게 다운받으시고 최고로 멋지게 연주하세요!</li>" +
        "<li>감사합니다. 좋은 하루 되세요~</li>",
};
