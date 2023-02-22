import { Category } from "./category";

export interface Food {
	foodId:number;
    foodName:string;
    category:Category;
    calPerQty:number;
    units:string;
}