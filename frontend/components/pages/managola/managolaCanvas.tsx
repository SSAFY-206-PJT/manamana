import { MutableRefObject, useEffect, useRef, useState } from "react";


export default function ManagolaCanvas() {
    const canvasRef = useRef<any>();
    let context: any;
    const [width, setWidth] = useState<number>(0);
    const [height, setHeight] = useState<number>(0);

    useEffect(() => {
        context = canvasRef.current.getContext('2d');
        console.log(context);

        canvasRef.current.addEventListener("click", (event: any) => {
            let rect = canvasRef.current.getBoundingClientRect();
            let x = event.clientX - rect.left;
            let y = event.clientY - rect.top;

            console.log("(" + x + ", " + y + ") is clicked.");
        })
    }, []);

    useEffect(() => {
        if (!canvasRef) return;

        setWidth(window.innerWidth);
        setHeight(window.innerHeight)

        const img = new Image();
        img.src = "/images/Temp_Webtoon_Thumnail.jpg";
        img.style.borderRadius = '20';

        img.onload = () => {
            console.log("나와라!");
            context.save();
            roundedImage((Math.floor(width / 2) - 100), height - 400, 200, 300, 10);
            context.clip();
            context.drawImage(img, (Math.floor(width / 2) - 100), height - 400, 200, 300);
            context.restore();
        };


    }, [canvasRef]);


    function roundedImage(x: number, y: number, width: number, height: number, radius: number) {
        context.beginPath();
        context.moveTo(x + radius, y);
        context.lineTo(x + width - radius, y);
        context.quadraticCurveTo(x + width, y, x + width, y + radius);
        context.lineTo(x + width, y + height - radius);
        context.quadraticCurveTo(x + width, y + height, x + width - radius, y + height);
        context.lineTo(x + radius, y + height);
        context.quadraticCurveTo(x, y + height, x, y + height - radius);
        context.lineTo(x, y + radius);
        context.quadraticCurveTo(x, y, x + radius, y);
        context.closePath();
    }

    return (
        <div className="w-full h-full">
            <canvas width={width} height={height} ref={canvasRef} className="w-full h-full bg-green-900">

            </canvas>
        </div>
    )
}