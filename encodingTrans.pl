#! /usr/bin/perl

$irfile=0;
$count=0;
$folder=0;
print "Translation begin, from GBK to utf-8 \n";

my @coc = glob("*");
my @flag;
while($#coc != 0){
	
	for $file (@coc){
		@coc = grep { !/$file/ } @coc;
		if( -d $file){
			$folder++;
			@t = glob($file."/*");
			for $temp (@t){
				if($temp =~ /\.java/ ||  -d $temp){
					push @coc, $temp;
				}else{
					$irfile++;
				}
			}
		}else{
			$count++;
			if($file =~ "\.java"){
				$des = $file."_";
				$out = `file -bi $file`;
				($oh) = $out =~ /.*=(.*)/;
				$oh =~ s/iso-8859-1/GBK/;
				`iconv -f $oh -t UTF-8 $file>$des`;
				`mv $des $file`;
				($name) = $file =~ /^.*\/(.*)/;
				print "- ".$name."   $oh\n";
			}
		}
		
	}
}

print "\n Work Done, counter: ".$count." folder: ".$folder." irfile: ".$irfile;

