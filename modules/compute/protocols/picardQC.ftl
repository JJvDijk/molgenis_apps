#
# =====================================================
# $Id$
# $URL$
# $LastChangedDate$
# $LastChangedRevision$
# $LastChangedBy$
# =====================================================
#

<#include "macros.ftl"/>
<@begin/>
#MOLGENIS walltime=35:59:00 mem=4

inputs "${sortedbam}"
inputs "${indexfile}"
outputs "${alignmentmetrics}"
outputs "${gcbiasmetrics}"
outputs "${gcbiasmetricspdf}"
outputs "${insertsizemetrics}"
outputs "${insertsizemetricspdf}"
outputs "${meanqualitybycycle}"
outputs "${meanqualitybycyclepdf}"
outputs "${qualityscoredistribution}"
outputs "${qualityscoredistributionpdf}"
outputs "${hsmetrics}"
outputs "${bamindexstats}"

java -jar -Xmx4g ${alignmentmetricsjar} \
I=${sortedbam} \
O=${alignmentmetrics} \
R=${indexfile} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar ${gcbiasmetricsjar} \
R=${indexfile} \
I=${sortedbam} \
O=${gcbiasmetrics} \
CHART=${gcbiasmetricspdf} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar ${insertsizemetricsjar} \
I=${sortedbam} \
O=${insertsizemetrics} \
H=${insertsizemetricspdf} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar ${meanqualitybycyclejar} \
I=${sortedbam} \
O=${meanqualitybycycle} \
CHART=${meanqualitybycyclepdf} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar ${qualityscoredistributionjar} \
I=${sortedbam}
O=${qualityscoredistribution} \
CHART=${qualityscoredistributionpdf} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar -Xmx4g ${hsmetricsjar} \
INPUT=${sortedbam} \
OUTPUT=${hsmetrics} \
BAIT_INTERVALS=${baitintervals} \
TARGET_INTERVALS=${targetintervals} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir}

java -jar ${bamindexstatsjar} \
INPUT=${sortedbam} \
VALIDATION_STRINGENCY=LENIENT \
TMP_DIR=${tempdir} \
> ${bamindexstats}
<@end />