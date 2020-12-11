require python3-setuptools.inc

PROVIDES = "python3-distribute"

inherit setuptools3

RREPLACES_${PN} = "python3-distribute"
RPROVIDES_${PN} = "python3-distribute"
RCONFLICTS_${PN} = "python3-distribute"

