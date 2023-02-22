import { Food } from "./food";

export interface Category {
	categoryId:number;
    categoryName:string;
    foodList:Food[];
}