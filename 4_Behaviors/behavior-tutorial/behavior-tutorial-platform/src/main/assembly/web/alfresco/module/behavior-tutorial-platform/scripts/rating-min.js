function computeAverage(f){var c=f.parent;if(!c.hasAspect("{http://www.someco.com/model/ratings/1.0}rateable")){logger.log("Rating's parent ref did not have rateable aspect.");return}var a=c.children;var e=0;var d=0;if(a!=null&&a.length>0){for(i in a){var g=a[i];var b=g.properties["{http://www.someco.com/model/content/1.0}rating"];d+=b}e=d/a.length}logger.log("Computed average:"+e);c.properties["{http://www.someco.com/model/ratings/1.0}averageRating"]=e;c.properties["{http://www.someco.com/model/ratings/1.0}totalRating"]=d;c.properties["{http://www.someco.com/model/ratings/1.0}ratingCount"]=a.length;c.save();logger.log("Property set")};