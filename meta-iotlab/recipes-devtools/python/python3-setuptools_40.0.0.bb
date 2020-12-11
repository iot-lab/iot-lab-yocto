require python-setuptools.inc

PROVIDES = "python-distribute"

inherit setuptools3

RREPLACES_${PN} = "python-distribute"
RPROVIDES_${PN} = "python-distribute"
RCONFLICTS_${PN} = "python-distribute"

