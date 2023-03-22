import type { NextApiRequest, NextApiResponse } from "next";

interface author{
    id: number;
    name: string;
}

type Data = {
    id: number;
    name: string;
    status: string;
    imagePath: string;
    authors: author[];
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<Data[]>
){
    
}