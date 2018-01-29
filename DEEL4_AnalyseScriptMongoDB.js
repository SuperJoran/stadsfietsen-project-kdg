// Alle metingen uit Antwerpen
db.meting.find({stad: "Antwerpen"});


//Alle metingen uit Antwerpen met CO2 waarden hoger dan 60
db.meting.find(
	{stad: "Antwerpen", 
	"meting.co2" :{$gt:60} 
});


//Alle metingen uit Antwerpen met CO2 waarden hoger dan 60 die gemeten werden tussen 18:00 en 19:00 op 1 oktober 2014
db.meting.find(
    {stad: "Antwerpen", 
    "meting.co2" :{$gt:60},
    datum: {$gte:ISODate("2014-10-01T18:00:00Z"), $lt: ISODate("2014-10-01T19:00:00Z")}
});


// Alle metingen in een straal van 10km rond Antwerpen
db.meting.find( { loc: 
    { $near: 
        { $geometry:  
            {type: "Point",
                coordinates: [4.402, 51.219]},
                $maxDistance: 10000
            }}});
            
// Heat-map support:            
// Alle metingen voor CO2 worden (gegroepeerd per uur en per locatie) (geordend per uur) in de nieuwe tabel C02metingen gestopt         
db.meting.aggregate([
     {$group: {_id:  {
         stad: "$stad",
         hour: {$hour: "$datum" }},
         averageValue: {$avg: "$meting.co2"}
         }},
      {$project: {_id: 0, hour: "$_id.hour", stad: "$_id.stad", averageValue: "$averageValue"}},
      { $sort: {hour: 1}},
      {$out: "CO2metingen"}
   ]
)
// Alle metingen voor luchtvochtigheid worden (gegroepeerd per uur en per locatie) (geordend per uur) in de nieuwe tabel vochtigheidmetingen gestopt         
db.meting.aggregate([
     {$group: {_id:  {
         stad: "$stad",
         hour: {$hour: "$datum" }},
         averageValue: {$avg: "$meting.relatieve_vochtigheid"}
         }},
      {$project: {_id: 0, hour: "$_id.hour", stad: "$_id.stad", averageValue: "$averageValue"}},
      { $sort: {hour: 1}},
      {$out: "vochtigheidmetingen"}
   ]
)

// Alle metingen voor fijn stof worden (gegroepeerd per uur en per locatie) (geordend per uur) in de nieuwe tabel C02metingen gestopt         
db.meting.aggregate([
     {$group: {_id:  {
         stad: "$stad",
         hour: {$hour: "$datum" }},
         averageValue: {$avg: "$meting.fijn_stof"}
         }},
      {$project: {_id: 0, hour: "$_id.hour", stad: "$_id.stad", averageValue: "$averageValue"}},
      { $sort: {hour: 1}},
      {$out: "fijnstofmetingen"}
   ]
)
