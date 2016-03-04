select

br.[name_en] as Brand,
mod.[name_ru] as Model,
tr.[price] as Price,
curr.[name_en] as Currency,
tr.[date_of_issue] as Date_Of_Issue

from Car
inner join [Transport] tr on tr.[id]=car.[transport_id]
inner join [hb_Model] mod on mod.[id]=car.[model_id]
inner join [hb_Brand] br on br.[id]=tr.[brand_id]
inner join [hb_Currency] curr on curr.[id]=tr.[currency_id]